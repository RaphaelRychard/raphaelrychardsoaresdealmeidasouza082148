package br.gov.mt.seplag.artistalbumapi.modules.regional.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response.SyncRegionalResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.service.RegionalSyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regionals")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Regionals", description = "Endpoints para gerenciamento de regionais")
@Slf4j
public class RegionalSyncController {

    @Autowired
    private RegionalSyncService regionalSyncService;

    @PostMapping("/sync")
    @Operation(
            summary = "Sincronizar regionais",
            description = "Sincroniza regionais com a API externa. Insere novos, inativa ausentes e atualiza modificados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Sincronização concluída com sucesso",
                    content = @Content(schema = @Schema(implementation = SyncRegionalResponseDTO.class))
            ),
            @ApiResponse(responseCode = "500", description = "Erro ao sincronizar com API externa")
    })
    public ResponseEntity<SyncRegionalResponseDTO> syncRegionals() {
        SyncRegionalResponseDTO result = regionalSyncService.syncRegionals();
        return ResponseEntity.ok(result);
    }
}