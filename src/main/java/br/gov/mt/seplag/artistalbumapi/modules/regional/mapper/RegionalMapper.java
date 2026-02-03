package br.gov.mt.seplag.artistalbumapi.modules.regional.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.request.CreateRegionalRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import org.springframework.stereotype.Component;

@Component
public class RegionalMapper {
    public Regional toDomain(CreateRegionalRequestDTO dto) {
        return Regional.builder()
                .externalId(dto.getExternalId())
                .name(dto.getName())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }
}
