package br.gov.mt.seplag.artistalbumapi.modules.album.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.CreateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.AlbumEntity;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    public static AlbumEntity toDomain(CreateAlbumRequestDTO dto) {
        return AlbumEntity.builder()
                .title(dto.getTitle())
                .releaseYear(dto.getReleaseYear())
                .coverImageKey(dto.getCoverImageKey())
                .build();
    }
}
