package br.gov.mt.seplag.artistalbumapi.modules.artist.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import br.gov.mt.seplag.artistalbumapi.modules.artist.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateArtistUseCase {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist execute(Artist artistEntity) {
        return this.artistRepository.save(artistEntity);
    }
}
