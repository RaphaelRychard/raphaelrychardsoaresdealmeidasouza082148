package br.gov.mt.seplag.artistalbumapi.modules.album.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.CreateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.UpdateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class AlbumMapper {
    public static Album toDomain(CreateAlbumRequestDTO dto) {
        return Album.builder()
                .title(dto.title())
                .releaseYear(Year.of(dto.releaseYear()))
                .build();
    }

    public static void updateDomain(Album album, UpdateAlbumRequestDTO dto) {
        album.setTitle(dto.title());
        album.setReleaseYear(Year.of(dto.releaseYear()));
        album.setCoverImageKey(dto.coverImageKey());
    }
}
