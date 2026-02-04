package br.gov.mt.seplag.artistalbumapi.modules.album.controller;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.GetAlbumByIdUseCase;
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
public class GetAlbumByIdController {

    @Autowired
    private GetAlbumByIdUseCase getAlbumByIdUseCase;

    @Operation(summary = "Obtém um álbum pelo ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Álbum encontrado"),})
    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> getById(@Parameter(description = "ID do álbum", required = true) @PathVariable Long id) {

        var album = getAlbumByIdUseCase.execute(id);
        return ResponseEntity.ok(AlbumPresenter.toResponse(album));
    }
}
