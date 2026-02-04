package br.gov.mt.seplag.artistalbumapi.modules.artist.controller;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.SearchArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.ArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.presenter.ArtistPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.artist.useCases.SearchArtistsByNameUseCase;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist/search")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Artists")
@RequiredArgsConstructor
public class SearchArtistsByNameController {

    private final SearchArtistsByNameUseCase searchArtistsByNameUseCase;

    @Operation(summary = "Buscar artistas por nome", description = "Retorna artistas filtrados por nome com paginação e ordenação")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Lista de artistas encontrada")})
    @GetMapping
    public ResponseEntity<SimplePageResponseDTO<ArtistResponseDTO>> searchByName(@Valid @ParameterObject SearchArtistRequestDTO request) {
        var result = searchArtistsByNameUseCase.execute(request.name(), request.toPageable());
        return ResponseEntity.ok(ArtistPresenter.toPageResponse(result));
    }
}