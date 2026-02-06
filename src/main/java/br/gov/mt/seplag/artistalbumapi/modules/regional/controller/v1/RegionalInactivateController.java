package br.gov.mt.seplag.artistalbumapi.modules.regional.controller.v1;

import br.gov.mt.seplag.artistalbumapi.modules.regional.useCases.InactivateRegionalUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/regionals")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Regionals", description = "Endpoints para gerenciamento de regionais")
public class RegionalInactivateController {

    @Autowired
    private InactivateRegionalUseCase inactivateRegionalUseCase;

    @PatchMapping("/{id}/inactivate")
    @Operation(
            summary = "Inativar regional",
            description = "Inativa manualmente uma regional ativa no sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Regional inativada com sucesso"
            )
    })
    public ResponseEntity<Void> inactivate(@PathVariable Long id) {
        inactivateRegionalUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}