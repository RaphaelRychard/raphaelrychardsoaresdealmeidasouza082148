package br.gov.mt.seplag.artistalbumapi.modules.album.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "DTO para criar um álbum")
public record CreateAlbumRequestDTO(

        @NotBlank
        @Size(max = 200)
        @Schema(description = "Título do álbum", example = "Hybrid Theory")
        String title,

        @NotNull
        @Min(1900)
        @Max(2100)
        @Schema(description = "Ano de lançamento", example = "2000")
        Integer releaseYear,

        @NotNull
        @Size(min = 1)
        @Schema(description = "Lista de IDs dos artistas", example = "[7, 8]")
        List<Long> artistIds
) {}
