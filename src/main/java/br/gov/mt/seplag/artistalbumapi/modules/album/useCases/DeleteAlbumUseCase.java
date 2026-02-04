package br.gov.mt.seplag.artistalbumapi.modules.album.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.album.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAlbumUseCase {

    private final AlbumRepository albumRepository;

    public void execute(Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));

        albumRepository.delete(album);
    }
}
