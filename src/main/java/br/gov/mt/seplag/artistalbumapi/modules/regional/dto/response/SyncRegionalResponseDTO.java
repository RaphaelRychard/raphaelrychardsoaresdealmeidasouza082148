package br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyncRegionalResponseDTO {
    
    private Integer totalFetched;
    private Integer inserted;
    private Integer inactivated;
    private Integer updated;
    private String message;
}
