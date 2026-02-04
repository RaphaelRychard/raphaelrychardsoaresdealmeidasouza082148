package br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response;

import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "DTO de resposta para artista")
public record ArtistResponseDTO(

        @Schema(description = "ID do artista", example = "1")
        Long id,

        @Schema(description = "Nome do artista", example = "Legião Urbana")
        String name,

        @Schema(description = "Tipo do artista", example = "BAND")
        ArtistType artistType,

        @Schema(description = "Data de criação do registro", example = "2026-02-03T21:40:00")
        LocalDateTime createdAt,

        @Schema(description = "Data de atualização do registro", example = "2026-02-03T21:40:00")
        LocalDateTime updatedAt
) {}
