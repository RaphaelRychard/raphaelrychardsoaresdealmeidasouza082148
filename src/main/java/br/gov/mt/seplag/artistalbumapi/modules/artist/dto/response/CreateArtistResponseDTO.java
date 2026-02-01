package br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response;

import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateArtistResponseDTO {

    private Long id;
    private String name;
    private ArtistType artistType;
}
