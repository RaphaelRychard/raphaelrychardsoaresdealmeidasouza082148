package br.gov.mt.seplag.artistalbumapi.modules.regional.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * DTO para mapear a resposta da API externa de regionais
 * Endpoint: https://integrador-argus-api.geia.vip/v1/regionais
 */
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
