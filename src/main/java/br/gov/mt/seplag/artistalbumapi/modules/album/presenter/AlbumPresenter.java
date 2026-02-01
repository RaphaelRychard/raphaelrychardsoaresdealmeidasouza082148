package br.gov.mt.seplag.artistalbumapi.modules.album.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.CreateAlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.AlbumEntity;

public class AlbumPresenter {
    public static CreateAlbumResponseDTO toResponse(AlbumEntity entity) {
        return CreateAlbumResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .releaseYear(entity.getReleaseYear())
                .coverImageKey(entity.getCoverImageKey())
                .build();
    }
}
