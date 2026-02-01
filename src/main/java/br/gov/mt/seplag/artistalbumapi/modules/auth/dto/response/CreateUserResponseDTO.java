package br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseDTO {
    private UUID userId;
}
