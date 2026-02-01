package br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request;

import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArtistRequestDTO {
    private String name;
    private ArtistType artistType;
}
