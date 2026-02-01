package br.gov.mt.seplag.artistalbumapi.modules.regional.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.request.CreateRegionalRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.RegionalEntity;
import org.springframework.stereotype.Component;

@Component
public class RegionalMapper {
    public RegionalEntity toDomain(CreateRegionalRequestDTO dto) {
        return RegionalEntity.builder()
                .externalId(dto.getExternalId())
                .name(dto.getName())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }
}
