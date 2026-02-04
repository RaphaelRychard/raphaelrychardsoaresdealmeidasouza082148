package br.gov.mt.seplag.artistalbumapi.modules.album.dto.request;

import jakarta.validation.constraints.*;

public record UpdateAlbumRequestDTO(

        @NotBlank
        @Size(max = 200)
        String title,

        @NotNull
        @Min(1000)
        @Max(3000)
        Integer releaseYear,

        @NotBlank
        @Size(max = 200)
        String coverImageKey

) {}
