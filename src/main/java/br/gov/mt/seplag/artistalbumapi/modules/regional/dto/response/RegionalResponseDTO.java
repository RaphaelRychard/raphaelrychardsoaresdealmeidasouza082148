package br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionalResponseDTO {
    
    private Long id;
    private Integer externalId;
    private String name;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
