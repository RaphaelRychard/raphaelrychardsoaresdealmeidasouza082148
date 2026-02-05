package br.gov.mt.seplag.artistalbumapi.modules.artist.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.UpdateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.ArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.presenter.ArtistPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.artist.useCases.UpdateArtistUseCase;
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
@RequestMapping("/api/v1/artist")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Artists")
public class UpdateArtistController {

    @Autowired
    private UpdateArtistUseCase updateArtistUseCase;

    @Operation(summary = "Atualizar artista", description = "Atualiza os dados de um artista existente")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso")})
    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateArtistRequestDTO dto) {
        var artist = updateArtistUseCase.execute(id, dto);
        return ResponseEntity.ok(ArtistPresenter.toResponse(artist));
    }
}
