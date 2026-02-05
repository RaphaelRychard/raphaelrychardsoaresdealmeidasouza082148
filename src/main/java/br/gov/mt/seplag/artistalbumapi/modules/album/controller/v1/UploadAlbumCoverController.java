package br.gov.mt.seplag.artistalbumapi.modules.album.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.UploadAlbumCoverUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/albums")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Albums")
public class UploadAlbumCoverController {

    @Autowired
    private UploadAlbumCoverUseCase uploadAlbumCoverUseCase;

    @Operation(summary = "Upload de capa do álbum", description = "Faz upload da imagem de capa do álbum")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Imagem enviada com sucesso")})
    @PostMapping(value = "/{id}/cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AlbumResponseDTO> uploadCover(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        var album = uploadAlbumCoverUseCase.execute(id, file);
        return ResponseEntity.ok(AlbumPresenter.toResponse(album));
    }
}