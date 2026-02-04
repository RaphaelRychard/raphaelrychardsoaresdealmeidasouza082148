package br.gov.mt.seplag.artistalbumapi.modules.album.controller;

import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.DeleteAlbumUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Albums")
public class DeleteAlbumController {

    @Autowired
    private DeleteAlbumUseCase deleteAlbumUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteAlbumUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
