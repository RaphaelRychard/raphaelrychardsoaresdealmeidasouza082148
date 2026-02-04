package br.gov.mt.seplag.artistalbumapi.modules.album.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;

public class AlbumPresenter {
    public static AlbumResponseDTO toResponse(Album entity) {
        return new AlbumResponseDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getReleaseYear().getValue(),
                entity.getCoverImageKey(),
                entity.getCreatedAt()
        );
    }
}
