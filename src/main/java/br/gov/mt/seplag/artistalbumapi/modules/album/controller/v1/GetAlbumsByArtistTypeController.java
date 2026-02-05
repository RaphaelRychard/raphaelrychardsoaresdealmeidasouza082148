package br.gov.mt.seplag.artistalbumapi.modules.album.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.GetAlbumsByArtistTypeUseCase;
import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/album/type")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Albums")
public class GetAlbumsByArtistTypeController {

    @Autowired
    private GetAlbumsByArtistTypeUseCase getAlbumsByArtistTypeUseCase;

    @Operation(summary = "Listar álbuns filtrando por tipo de artista")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Lista de álbuns filtrada por tipo retornada")})
    @GetMapping("/{artistType}")
    public ResponseEntity<SimplePageResponseDTO<AlbumResponseDTO>> getByArtistType(@PathVariable ArtistType artistType, @Valid @ParameterObject SimplePageRequestDTO simplePage) {
        var result = getAlbumsByArtistTypeUseCase.execute(artistType, simplePage.toPageable());
        return ResponseEntity.ok(AlbumPresenter.toPageResponse(result));
    }
}