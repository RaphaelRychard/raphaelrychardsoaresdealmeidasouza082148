package br.gov.mt.seplag.artistalbumapi.modules.artist.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.CreateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.UpdateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {

    public static Artist toDomain(CreateArtistRequestDTO dto) {
        return Artist.builder()
                .name(dto.name())
                .artistType(dto.artistType())
                .build();
    }

    public static void updateDomain(Artist artist, UpdateArtistRequestDTO dto) {
        if (dto.name() != null) artist.setName(dto.name());
        if (dto.artistType() != null) artist.setArtistType(dto.artistType());
    }
}
