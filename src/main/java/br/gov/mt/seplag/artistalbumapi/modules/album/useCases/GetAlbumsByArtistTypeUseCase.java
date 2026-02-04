package br.gov.mt.seplag.artistalbumapi.modules.album.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.album.repository.AlbumRepository;
import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAlbumsByArtistTypeUseCase {

    @Autowired
    private AlbumRepository albumRepository;

    public SimplePageResponseDTO<Album> execute(ArtistType artistType, Pageable pageable) {
        Page<Album> albumPage = albumRepository.findByArtists_ArtistType(artistType, pageable);

        return new SimplePageResponseDTO<>(
                albumPage.getContent(),
                albumPage.getTotalElements(),
                albumPage.getTotalPages()
        );
    }
}
