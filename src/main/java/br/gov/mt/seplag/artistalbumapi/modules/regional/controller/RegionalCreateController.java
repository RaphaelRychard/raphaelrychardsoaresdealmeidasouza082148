package br.gov.mt.seplag.artistalbumapi.modules.regional.controller;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.request.CreateRegionalRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.mapper.RegionalMapper;
import br.gov.mt.seplag.artistalbumapi.modules.regional.useCases.CreateRegionalUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/regionals")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Regionals", description = "Endpoints para gerenciamento de regionais")
public class RegionalCreateController {

    @Autowired
    private CreateRegionalUseCase createRegionalUseCase;

    @Autowired
    private RegionalMapper regionalMapper;

    @PostMapping
    @Operation(summary = "Criar regional", description = "Cria uma nova regional manualmente no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Regional criada com sucesso"),
    })
    public ResponseEntity<String> create(@Valid @RequestBody CreateRegionalRequestDTO requestDTO) {
        var regionalEntity = regionalMapper.toDomain(requestDTO);
        createRegionalUseCase.execute(regionalEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Regional created successfully");
    }
}