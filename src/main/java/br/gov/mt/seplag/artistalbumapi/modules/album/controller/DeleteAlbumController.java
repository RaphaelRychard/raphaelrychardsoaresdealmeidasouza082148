package br.gov.mt.seplag.artistalbumapi.modules.album.controller;

import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.DeleteAlbumUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Exclui um álbum existente")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Álbum excluído com sucesso"),})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID do álbum a ser excluído", required = true) @PathVariable Long id) {

        deleteAlbumUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
