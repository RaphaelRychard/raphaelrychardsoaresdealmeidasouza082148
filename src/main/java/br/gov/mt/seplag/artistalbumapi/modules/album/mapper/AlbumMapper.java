package br.gov.mt.seplag.artistalbumapi.modules.album.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.CreateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    public static Album toDomain(CreateAlbumRequestDTO dto) {
        return Album.builder()
                .title(dto.getTitle())
                .releaseYear(dto.getReleaseYear())
                .coverImageKey(dto.getCoverImageKey())
                .build();
    }
}
