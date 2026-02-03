package br.gov.mt.seplag.artistalbumapi.modules.artist.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.CreateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;

public class ArtistMapper {
    public static Artist toDomain(CreateArtistRequestDTO dto) {
        return Artist.builder()
                .name(dto.getName())
                .artistType(dto.getArtistType())
                .build();
    }
}
