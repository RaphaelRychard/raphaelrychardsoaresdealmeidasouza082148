package br.gov.mt.seplag.artistalbumapi.modules.artist.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.CreateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.ArtistEntity;

public class ArtistMapper {
    public static ArtistEntity toDomain(CreateArtistRequestDTO dto) {
        return ArtistEntity.builder()
                .name(dto.getName())
                .artistType(dto.getArtistType())
                .build();
    }
}
