package br.gov.mt.seplag.artistalbumapi.modules.album.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAlbumRequestDTO(

        @NotBlank
        @Schema(
                description = "Album title",
                example = "Hybrid Theory"
        )
        String title,

        @NotNull
        @Min(1000)
        @Max(3000)
        @Schema(
                description = "Album release year",
                example = "2024",
                minimum = "1000",
                maximum = "3000"
        )
        Integer releaseYear,

        @NotBlank
        @Schema(
                description = "S3 key or storage identifier of the album cover image",
                example = "albums/hybrid-theory.png"
        )
        String coverImageKey
) {}
