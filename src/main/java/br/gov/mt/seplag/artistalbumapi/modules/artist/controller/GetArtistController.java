package br.gov.mt.seplag.artistalbumapi.modules.artist.controller;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.ArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.presenter.ArtistPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.artist.useCases.GetArtistByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Artists")
public class GetArtistController {

    @Autowired
    private GetArtistByIdUseCase getArtistByIdUseCase;

    @Operation(summary = "Buscar artista por ID", description = "Retorna os dados de um artista específico pelo ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Artista encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getById(@PathVariable Long id) {
        var artist = getArtistByIdUseCase.execute(id);
        return ResponseEntity.ok(ArtistPresenter.toResponse(artist));
    }
}
