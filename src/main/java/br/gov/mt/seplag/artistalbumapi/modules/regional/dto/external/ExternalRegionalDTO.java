package br.gov.mt.seplag.artistalbumapi.modules.regional.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalRegionalDTO {
    
    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("nome")
    private String nome;
}
