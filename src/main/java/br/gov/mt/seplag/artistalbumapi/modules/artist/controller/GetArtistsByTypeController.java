package br.gov.mt.seplag.artistalbumapi.modules.artist.controller;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.GetArtistsByTypeRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.ArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.enums.ArtistType;
import br.gov.mt.seplag.artistalbumapi.modules.artist.presenter.ArtistPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.artist.useCases.GetArtistsByTypeUseCase;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/artist/type")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Artists")
public class GetArtistsByTypeController {

    @Autowired
    private GetArtistsByTypeUseCase getArtistsByTypeUseCase;

    @Operation(summary = "Listar artistas por tipo", description = "Retorna artistas filtrados por tipo (SOLO/BAND) com paginação e ordenação")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Lista de artistas por tipo retornada")})
    @GetMapping("/{type}")
    public ResponseEntity<SimplePageResponseDTO<ArtistResponseDTO>> getByType(
            @Parameter(description = "Tipo do artista (SOLO ou BAND)", example = "SOLO")
            @PathVariable ArtistType type,

            @Valid @ParameterObject GetArtistsByTypeRequestDTO request
    ) {
        var result = getArtistsByTypeUseCase.execute(type, request.toPageable());
        return ResponseEntity.ok(ArtistPresenter.toPageResponse(result));
    }
}