package br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRegionalResponseDTO {
    private Long id;
    private Integer externalId;
    private String name;
    private Boolean active;
}