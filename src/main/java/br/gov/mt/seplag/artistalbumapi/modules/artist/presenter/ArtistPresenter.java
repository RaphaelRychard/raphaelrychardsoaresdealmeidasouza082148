package br.gov.mt.seplag.artistalbumapi.modules.artist.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.ArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;

public class ArtistPresenter {

    public static ArtistResponseDTO toResponse(Artist entity) {
        return new ArtistResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getArtistType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
