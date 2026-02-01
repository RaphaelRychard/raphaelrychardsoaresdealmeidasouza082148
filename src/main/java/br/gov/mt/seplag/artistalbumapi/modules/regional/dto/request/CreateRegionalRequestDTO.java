package br.gov.mt.seplag.artistalbumapi.modules.regional.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRegionalRequestDTO {

    @NotNull
    private Integer externalId;

    @NotBlank
    private String name;

    private Boolean active = true;
}
