package br.gov.mt.seplag.artistalbumapi.modules.regional.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response.RegionalResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;

import java.util.List;
import java.util.stream.Collectors;

public class RegionalPresenter {
    public static RegionalResponseDTO toDetailedResponse(Regional entity) {
        return RegionalResponseDTO.builder()
                .id(entity.getId())
                .externalId(entity.getExternalId())
                .name(entity.getName())
                .active(entity.getActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static List<RegionalResponseDTO> toDetailedResponseList(List<Regional> entities) {
        return entities.stream()
                .map(RegionalPresenter::toDetailedResponse)
                .collect(Collectors.toList());
    }
}
