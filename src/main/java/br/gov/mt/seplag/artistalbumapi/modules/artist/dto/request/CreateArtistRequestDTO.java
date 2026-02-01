package br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request;

import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateArtistRequestDTO {

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private ArtistType artistType;
}
