package br.gov.mt.seplag.artistalbumapi.modules.album.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.request.CreateAlbumRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.mapper.AlbumMapper;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.CreateAlbumUseCase;
import io.swagger.v3.oas.annotations.Operation;
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
public class CreateAlbumController {

    @Autowired
    private CreateAlbumUseCase createAlbumUseCase;

    @Operation(summary = "Cria um novo álbum", description = "Cria um álbum associado a um artista existente")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Álbum criado com sucesso"),})
    @PostMapping
    public ResponseEntity<AlbumResponseDTO> create(@Valid @RequestBody CreateAlbumRequestDTO albumRequestDTO) {
        var albumEntityToDomain = AlbumMapper.toDomain(albumRequestDTO);
        var result = createAlbumUseCase.execute(albumEntityToDomain);

        return ResponseEntity.ok(AlbumPresenter.toResponse(result));
    }
}
