package br.gov.mt.seplag.artistalbumapi.modules.regional.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import br.gov.mt.seplag.artistalbumapi.modules.regional.exception.RegionalExternalIdAlreadyExistsException;
import br.gov.mt.seplag.artistalbumapi.modules.regional.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRegionalUseCase {

    @Autowired
    private RegionalRepository regionalRepository;

    @Transactional
    public void execute(Regional regional) {
        if (regional.getExternalId() == null) {
            throw new IllegalArgumentException("externalId is required");
        }

        boolean existsActive = regionalRepository.existsByExternalIdAndActiveTrue(regional.getExternalId());
        if (existsActive) {
            throw new RegionalExternalIdAlreadyExistsException(regional.getExternalId().toString());
        }

        regional.setActive(true);
        if (regional.getName() != null) {
            regional.setName(regional.getName().trim());
        }

        regionalRepository.save(regional);
    }
}