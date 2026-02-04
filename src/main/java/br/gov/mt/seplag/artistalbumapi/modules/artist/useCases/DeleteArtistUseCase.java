package br.gov.mt.seplag.artistalbumapi.modules.artist.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import br.gov.mt.seplag.artistalbumapi.modules.artist.exception.ArtistNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.artist.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteArtistUseCase {

    @Autowired
    private ArtistRepository artistRepository;

    public void execute(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(id));

        artistRepository.delete(artist);
    }
}
