package br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request;

import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para criar um artista")
public record CreateArtistRequestDTO(

        @NotBlank
        @Size(max = 200)
        @Schema(description = "Nome do artista", example = "Legião Urbana", required = true)
        String name,

        @NotNull
        @Schema(description = "Tipo do artista (SOLO ou BAND)", example = "BAND", required = true)
        ArtistType artistType
) {}
