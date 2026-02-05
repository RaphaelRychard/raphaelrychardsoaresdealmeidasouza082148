package br.gov.mt.seplag.artistalbumapi.modules.album.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.UpdateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.UpdateAlbumUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/album")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Albums")
public class UpdateAlbumController {

    @Autowired
    private UpdateAlbumUseCase updateAlbumUseCase;

    @Operation(summary = "Atualiza um álbum existente")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Álbum atualizado com sucesso"),})
    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDTO> update(@Parameter(description = "ID do álbum a ser atualizado", required = true) @PathVariable Long id, @Valid @RequestBody UpdateAlbumRequestDTO dto) {
        var album = updateAlbumUseCase.execute(id, dto);
        return ResponseEntity.ok(AlbumPresenter.toResponse(album));
    }
}
