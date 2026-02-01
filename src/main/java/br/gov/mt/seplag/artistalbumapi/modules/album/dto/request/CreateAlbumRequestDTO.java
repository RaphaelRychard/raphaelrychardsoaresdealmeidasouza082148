package br.gov.mt.seplag.artistalbumapi.modules.album.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAlbumRequestDTO {

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotNull
    private Integer releaseYear;

    @NotBlank
    @Size(max = 200)
    private String coverImageKey;
}
