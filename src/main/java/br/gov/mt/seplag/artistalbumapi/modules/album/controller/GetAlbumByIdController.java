package br.gov.mt.seplag.artistalbumapi.modules.album.controller;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.GetAlbumByIdUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Albums")
public class GetAlbumByIdController {

    @Autowired
    private GetAlbumByIdUseCase getAlbumByIdUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> getById(@PathVariable Long id) {
        var album = getAlbumByIdUseCase.execute(id);
        return ResponseEntity.ok(AlbumPresenter.toResponse(album));
    }
}
