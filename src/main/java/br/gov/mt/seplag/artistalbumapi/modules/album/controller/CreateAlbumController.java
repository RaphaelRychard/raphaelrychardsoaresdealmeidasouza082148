package br.gov.mt.seplag.artistalbumapi.modules.album.controller;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.CreateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.CreateAlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.mapper.AlbumMapper;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.CreateAlbumUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
public class CreateAlbumController {

    @Autowired
    private CreateAlbumUseCase createAlbumUseCase;

    @PostMapping
    public ResponseEntity<CreateAlbumResponseDTO> create(@Valid @RequestBody CreateAlbumRequestDTO albumRequestDTO) {
        var albumEntityToDomain = AlbumMapper.toDomain(albumRequestDTO);
        var result = createAlbumUseCase.execute(albumEntityToDomain);

        return ResponseEntity.ok(AlbumPresenter.toResponse(result));
    }
}
