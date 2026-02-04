package br.gov.mt.seplag.artistalbumapi.modules.album.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.UpdateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.entity.Album;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.AlbumNotFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.album.exception.InvalidAlbumReleaseYearException;
import br.gov.mt.seplag.artistalbumapi.modules.album.mapper.AlbumMapper;
import br.gov.mt.seplag.artistalbumapi.modules.album.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdateAlbumUseCase {

    private final AlbumRepository albumRepository;

    public Album execute(Long id, UpdateAlbumRequestDTO dto) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id));

        AlbumMapper.updateDomain(album, dto);

        if (!album.isReleaseYearValid()) {
            throw new InvalidAlbumReleaseYearException();
        }

        return albumRepository.save(album);
    }
}
