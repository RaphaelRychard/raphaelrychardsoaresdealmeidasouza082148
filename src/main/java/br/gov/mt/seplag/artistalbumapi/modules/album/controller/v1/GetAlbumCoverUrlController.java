package br.gov.mt.seplag.artistalbumapi.modules.album.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.GetAlbumCoverUrlUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/albums")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Albums")
public class GetAlbumCoverUrlController {

    @Autowired
    private GetAlbumCoverUrlUseCase getAlbumCoverUrlUseCase;

    @Operation(summary = "Obter URL da capa do álbum", description = "Retorna URL pré-assinada com expiração de 30 minutos")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "URL gerada com sucesso")})
    @GetMapping("/{id}/cover/url")
    public ResponseEntity<Map<String, String>> getCoverUrl(@PathVariable Long id) {
        String url = getAlbumCoverUrlUseCase.execute(id);
        return ResponseEntity.ok(Map.of("url", url, "expiresIn", "1800"));
    }
}