package br.gov.mt.seplag.artistalbumapi.modules.album.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.album.repository.AlbumRepository;
import br.gov.mt.seplag.artistalbumapi.modules.shared.services.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAlbumCoverUrlUseCase {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MinioService minioService;

    public String execute(Long albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException(albumId));

        if (album.getCoverImageKey() == null) {
            throw new IllegalArgumentException("Álbum não possui imagem de capa");
        }

        return minioService.getPresignedUrl(album.getCoverImageKey());
    }
}