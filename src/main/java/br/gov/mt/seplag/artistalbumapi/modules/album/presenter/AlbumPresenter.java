package br.gov.mt.seplag.artistalbumapi.modules.album.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;

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

    public static SimplePageResponseDTO<AlbumResponseDTO> toPageResponse(
            SimplePageResponseDTO<Album> page
    ) {
        return new SimplePageResponseDTO<>(
                page.content().stream().map(AlbumPresenter::toResponse).toList(),
                page.totalElements(),
                page.totalPages()
        );
    }
}
