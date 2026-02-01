package br.gov.mt.seplag.artistalbumapi.modules.artist.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.CreateArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.ArtistEntity;

public class ArtistPresenter {
    public static CreateArtistResponseDTO toResponse(ArtistEntity entity) {
        return CreateArtistResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .artistType(entity.getArtistType())
                .build();
    }
}
