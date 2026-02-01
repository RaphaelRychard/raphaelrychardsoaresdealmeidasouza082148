package br.gov.mt.seplag.artistalbumapi.modules.album.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.album.entity.AlbumEntity;
import br.gov.mt.seplag.artistalbumapi.modules.album.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAlbumUseCase {

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumEntity execute(AlbumEntity albumEntity) {
        return this.albumRepository.save(albumEntity);
    }
}
