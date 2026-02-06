package br.gov.mt.seplag.artistalbumapi.modules.regional.controller;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response.RegionalResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.entity.Regional;
import br.gov.mt.seplag.artistalbumapi.modules.regional.presenter.RegionalPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.regional.useCases.ListActiveRegionalsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regionals")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Regionals", description = "Endpoints para gerenciamento de regionais")
public class RegionalListActiveController {

    @Autowired
    private ListActiveRegionalsUseCase listActiveRegionalsUseCase;

    @GetMapping("/active")
    @Operation(
            summary = "Listar regionais ativas",
            description = "Retorna apenas as regionais que estão ativas no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de regionais ativas retornada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RegionalResponseDTO.class)))
            )
    })
    public ResponseEntity<List<RegionalResponseDTO>> listActive() {
        List<Regional> regionals = listActiveRegionalsUseCase.execute();
        List<RegionalResponseDTO> response = RegionalPresenter.toDetailedResponseList(regionals);
        return ResponseEntity.ok(response);
    }
}