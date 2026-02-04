package br.gov.mt.seplag.artistalbumapi.modules.artist.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import br.gov.mt.seplag.artistalbumapi.modules.artist.repository.ArtistRepository;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetArtistsByTypeUseCase {

    @Autowired
    private ArtistRepository artistRepository;

    public SimplePageResponseDTO<Artist> execute(ArtistType type, Pageable pageable) {
        Page<Artist> artistPage = artistRepository.findByArtistType(type, pageable);

        return new SimplePageResponseDTO<>(
                artistPage.getContent(),
                artistPage.getTotalElements(),
                artistPage.getTotalPages()
        );
    }
}