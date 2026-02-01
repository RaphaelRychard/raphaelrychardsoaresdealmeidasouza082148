package br.gov.mt.seplag.artistalbumapi.modules.regional.controller;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.request.CreateRegionalRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.mapper.RegionalMapper;
import br.gov.mt.seplag.artistalbumapi.modules.regional.useCases.CreateRegionalUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regionals")
public class CreateRegionalController {

    @Autowired
    private CreateRegionalUseCase createRegionalUseCase;

    @Autowired
    private RegionalMapper regionalMapper;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateRegionalRequestDTO requestDTO) {
        var regionalEntity = regionalMapper.toDomain(requestDTO);
        createRegionalUseCase.execute(regionalEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body("Regional create successfully");
    }
}