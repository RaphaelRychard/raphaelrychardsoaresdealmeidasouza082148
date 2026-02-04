package br.gov.mt.seplag.artistalbumapi.modules.artist.controller;

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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Artists")
@RequiredArgsConstructor
public class UpdateArtistController {

    @Autowired
    private final UpdateArtistUseCase updateArtistUseCase;

    @Operation(summary = "Atualizar artista", description = "Atualiza os dados de um artista existente")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso"), @ApiResponse(responseCode = "404", description = "Artista não encontrado"), @ApiResponse(responseCode = "400", description = "Dados inválidos")})
    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateArtistRequestDTO dto) {
        var artist = updateArtistUseCase.execute(id, dto);
        return ResponseEntity.ok(ArtistPresenter.toResponse(artist));
    }
}
