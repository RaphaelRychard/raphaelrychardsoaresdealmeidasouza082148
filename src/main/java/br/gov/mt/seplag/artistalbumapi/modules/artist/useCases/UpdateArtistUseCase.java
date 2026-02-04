package br.gov.mt.seplag.artistalbumapi.modules.artist.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.UpdateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import br.gov.mt.seplag.artistalbumapi.modules.artist.exception.ArtistNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.artist.mapper.ArtistMapper;
import br.gov.mt.seplag.artistalbumapi.modules.artist.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateArtistUseCase {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist execute(Long id, UpdateArtistRequestDTO dto) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(id));
        ArtistMapper.updateDomain(artist, dto);

        return artistRepository.save(artist);
    }
}
