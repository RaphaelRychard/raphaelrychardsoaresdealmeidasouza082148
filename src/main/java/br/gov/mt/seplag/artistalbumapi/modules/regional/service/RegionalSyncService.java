package br.gov.mt.seplag.artistalbumapi.modules.regional.service;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.external.ExternalRegionalDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response.SyncRegionalResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalSyncException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.repository.RegionalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RegionalSyncService {

    @Autowired
    private RegionalRepository regionalRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.external-api.regionals-url:https://integrador-argus-api.geia.vip/v1/regionais}")
    private String regionalsApiUrl;

    public List<ExternalRegionalDTO> fetchRegionalsFromAPI() {
        try {
            ResponseEntity<List<ExternalRegionalDTO>> response = restTemplate.exchange(
                    regionalsApiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ExternalRegionalDTO>>() {}
            );
            return Optional.ofNullable(response.getBody()).orElseGet(Collections::emptyList);
        } catch (Exception ex) {
            throw new RegionalSyncException("Failed to fetch regionals from external API", ex);
        }
    }

    @Transactional
    public SyncRegionalResponseDTO syncRegionals() {
        List<ExternalRegionalDTO> external = fetchRegionalsFromAPI();
        List<Regional> activeDb = regionalRepository.findByActiveTrue();

        int fetchedValid = (int) external.stream()
                .map(ExternalRegionalDTO::getId)
                .filter(Objects::nonNull)
                .count();

        Set<Integer> externalIdsFromEndpoint = external.stream()
                .map(ExternalRegionalDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Integer, List<Regional>> activesByExternalId = activeDb.stream()
                .filter(r -> r.getExternalId() != null)
                .collect(Collectors.groupingBy(Regional::getExternalId));

        int inserted = 0;
        int inactivated = 0;
        int updated = 0;

        for (ExternalRegionalDTO ext : external) {
            Integer extId = ext.getId();
            if (extId == null) continue;

            String extName = normalize(ext.getNome());

            List<Regional> currentActives = activesByExternalId.getOrDefault(extId, Collections.emptyList());

            if (currentActives.isEmpty()) {
                regionalRepository.save(newActive(extId, extName));
                inserted++;
                continue;
            }

            Regional main = currentActives.stream()
                    .max(Comparator.comparing(Regional::getId))
                    .orElseThrow();

            String currentName = normalize(main.getName());

            boolean nameChanged = !Objects.equals(currentName, extName);
            if (nameChanged) {
                for (Regional r : currentActives) {
                    r.setActive(false);
                }
                regionalRepository.saveAll(currentActives);
                inactivated += currentActives.size();

                regionalRepository.save(newActive(extId, extName));
                inserted++;
                updated++;
                continue;
            }

            if (currentActives.size() > 1) {
                List<Regional> toDisable = new ArrayList<>();
                for (Regional r : currentActives) {
                    if (!r.getId().equals(main.getId())) {
                        r.setActive(false);
                        toDisable.add(r);
                    }
                }
                if (!toDisable.isEmpty()) {
                    regionalRepository.saveAll(toDisable);
                    inactivated += toDisable.size();
                }
            }
        }

        // (2) Ausente no endpoint -> inativar
        List<Regional> toInactivate = activeDb.stream()
                .filter(r -> r.getExternalId() != null)
                .filter(r -> !externalIdsFromEndpoint.contains(r.getExternalId()))
                .collect(Collectors.toList());

        if (!toInactivate.isEmpty()) {
            for (Regional r : toInactivate) {
                r.setActive(false);
            }
            regionalRepository.saveAll(toInactivate);
            inactivated += toInactivate.size();
        }

        return SyncRegionalResponseDTO.builder()
                .totalFetched(fetchedValid)
                .inserted(inserted)
                .inactivated(inactivated)
                .updated(updated)
                .message("Synchronization completed successfully")
                .build();
    }

    private Regional newActive(Integer externalId, String name) {
        return Regional.builder()
                .externalId(externalId)
                .name(name)
                .active(true)
                .build();
    }

    private String normalize(String s) {
        return s == null ? "" : s.trim();
    }
}