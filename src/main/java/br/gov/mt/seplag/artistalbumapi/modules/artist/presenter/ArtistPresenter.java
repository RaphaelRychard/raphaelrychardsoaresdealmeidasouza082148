package br.gov.mt.seplag.artistalbumapi.modules.artist.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.ArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;

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

    public static SimplePageResponseDTO<ArtistResponseDTO> toPageResponse(SimplePageResponseDTO<Artist> page) {
        return new SimplePageResponseDTO<>(page.content().stream().map(
                ArtistPresenter::toResponse).toList(),
                page.totalElements(),
                page.totalPages()
        );
    }
}