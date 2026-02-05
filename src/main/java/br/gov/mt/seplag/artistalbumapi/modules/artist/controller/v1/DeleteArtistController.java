package br.gov.mt.seplag.artistalbumapi.modules.artist.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.artist.useCases.DeleteArtistUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artist")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Artists")
public class DeleteArtistController {

    @Autowired
    private DeleteArtistUseCase deleteArtistUseCase;

    @Operation(summary = "Excluir artista", description = "Remove um artista do sistema pelo ID")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Artista excluído com sucesso")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteArtistUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
