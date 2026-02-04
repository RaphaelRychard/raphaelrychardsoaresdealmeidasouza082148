package br.gov.mt.seplag.artistalbumapi.modules.shared.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Direção da ordenação")
public enum SortDirection {
    @Schema(description = "Ordenação ascendente (A-Z, 0-9)")
    ASC,

    @Schema(description = "Ordenação descendente (Z-A, 9-0)")
    DESC;

    public org.springframework.data.domain.Sort.Direction toSpringDirection() {
        return this == ASC
                ? org.springframework.data.domain.Sort.Direction.ASC
                : org.springframework.data.domain.Sort.Direction.DESC;
    }
}