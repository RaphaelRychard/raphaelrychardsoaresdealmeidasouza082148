package br.gov.mt.seplag.artistalbumapi.modules.artist.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.artist.entity.Artist;
import br.gov.mt.seplag.artistalbumapi.modules.artist.repository.ArtistRepository;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchArtistsByNameUseCase {

    private final ArtistRepository artistRepository;

    public SimplePageResponseDTO<Artist> execute(String name, Pageable pageable) {
        Page<Artist> artistPage = artistRepository.findByNameContainingIgnoreCase(name, pageable);

        return new SimplePageResponseDTO<>(
                artistPage.getContent(),
                artistPage.getTotalElements(),
                artistPage.getTotalPages()
        );
    }
}