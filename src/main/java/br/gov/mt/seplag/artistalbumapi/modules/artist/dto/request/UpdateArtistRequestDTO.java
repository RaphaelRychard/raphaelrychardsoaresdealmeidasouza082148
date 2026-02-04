package br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request;

import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para atualizar um artista")
public record UpdateArtistRequestDTO(

        @Size(max = 200)
        @Schema(description = "Nome do artista (opcional)", example = "Legião Urbana")
        String name,

        @Schema(description = "Tipo do artista (opcional)", example = "BAND")
        ArtistType artistType
) {}
