package br.gov.mt.seplag.artistalbumapi.modules.album.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.CreateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.UpdateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;

import java.time.Year;
import java.util.HashSet;
import java.util.stream.Collectors;

public class AlbumMapper {

    public static Album toDomain(CreateAlbumRequestDTO dto) {
        var album = Album.builder()
                .title(dto.title())
                .releaseYear(Year.of(dto.releaseYear()))
                .build();

        if (dto.artistIds() != null && !dto.artistIds().isEmpty()) {
            var artists = dto.artistIds().stream()
                    .map(id -> {
                        var a = new Artist();
                        a.setId(id);
                        return a;
                    })
                    .collect(Collectors.toSet());

            album.setArtists(new HashSet<>(artists));
        }

        return album;
    }

    public static void updateDomain(Album album, UpdateAlbumRequestDTO dto) {
        album.setTitle(dto.title());
        album.setReleaseYear(Year.of(dto.releaseYear()));
        album.setCoverImageKey(dto.coverImageKey());
    }
}