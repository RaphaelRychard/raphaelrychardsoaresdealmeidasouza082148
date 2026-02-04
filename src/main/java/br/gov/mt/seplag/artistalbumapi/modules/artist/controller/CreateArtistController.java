package br.gov.mt.seplag.artistalbumapi.modules.artist.controller;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.CreateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.ArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.mapper.ArtistMapper;
import br.gov.mt.seplag.artistalbumapi.modules.artist.presenter.ArtistPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.artist.useCases.CreateArtistUseCase;
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
public class CreateArtistController {

    @Autowired
    private CreateArtistUseCase createArtistUseCase;

    @Operation(summary = "Criar artista", description = "Cria um novo artista no sistema")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Artista criado com sucesso")})
    @PostMapping
    public ResponseEntity<ArtistResponseDTO> create(@Valid @RequestBody CreateArtistRequestDTO dto) {
        var artist = ArtistMapper.toDomain(dto);
        var saved = createArtistUseCase.execute(artist);
        return ResponseEntity.ok(ArtistPresenter.toResponse(saved));
    }
}
