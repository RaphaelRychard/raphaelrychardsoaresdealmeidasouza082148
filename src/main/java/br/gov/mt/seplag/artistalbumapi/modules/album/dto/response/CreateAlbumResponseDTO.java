package br.gov.mt.seplag.artistalbumapi.modules.album.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlbumResponseDTO {

    private Long id;
    private String title;
    private Integer releaseYear;
    private String coverImageKey;
}
