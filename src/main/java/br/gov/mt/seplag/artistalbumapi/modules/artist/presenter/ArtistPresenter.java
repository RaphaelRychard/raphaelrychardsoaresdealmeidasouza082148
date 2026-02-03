package br.gov.mt.seplag.artistalbumapi.modules.artist.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.CreateArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;

public class ArtistPresenter {
    public static CreateArtistResponseDTO toResponse(Artist entity) {
        return CreateArtistResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .artistType(entity.getArtistType())
                .build();
    }
}
