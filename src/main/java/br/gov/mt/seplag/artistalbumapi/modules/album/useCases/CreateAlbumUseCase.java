package br.gov.mt.seplag.artistalbumapi.modules.album.useCases;

import br.gov.mt.seplag.artistalbumapi.infra.exception.InvalidReleaseYearException;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumWithoutArtistException;
import br.gov.mt.seplag.artistalbumapi.modules.album.repository.AlbumRepository;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import br.gov.mt.seplag.artistalbumapi.modules.artist.exception.ArtistNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.artist.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CreateAlbumUseCase {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public CreateAlbumUseCase(
            AlbumRepository albumRepository,
            ArtistRepository artistRepository
    ) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public Album execute(Album album) {

        if (!album.isReleaseYearValid()) {
            throw new InvalidReleaseYearException();
        }

        if (album.getArtists() == null || album.getArtists().isEmpty()) {
            throw new AlbumWithoutArtistException();
        }

        var artistIds = album.getArtists()
                .stream()
                .map(Artist::getId)
                .toList();

        var artistsFromDb = artistRepository.findAllById(artistIds);

        if (artistsFromDb.size() != artistIds.size()) {
            throw new ArtistNotFoundException();
        }

        album.setArtists(new HashSet<>(artistsFromDb));

        return albumRepository.save(album);
    }
}