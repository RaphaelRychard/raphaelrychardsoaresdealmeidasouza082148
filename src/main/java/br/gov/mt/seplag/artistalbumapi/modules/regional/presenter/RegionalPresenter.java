package br.gov.mt.seplag.artistalbumapi.modules.regional.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response.CreateRegionalResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;

public class RegionalPresenter {
    public static CreateRegionalResponseDTO toResponse(Regional entity) {
        return CreateRegionalResponseDTO.builder()
                .id(entity.getId())
                .externalId(entity.getExternalId())
                .name(entity.getName())
                .active(entity.getActive())
                .build();
    }
}