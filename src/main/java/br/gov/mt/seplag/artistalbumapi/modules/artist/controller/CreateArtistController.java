package br.gov.mt.seplag.artistalbumapi.modules.artist.controller;

import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.request.CreateArtistRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.dto.response.CreateArtistResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.artist.mapper.ArtistMapper;
import br.gov.mt.seplag.artistalbumapi.modules.artist.presenter.ArtistPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.artist.useCases.CreateArtistUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artist")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Artists")
public class CreateArtistController {

    @Autowired
    private CreateArtistUseCase createArtistUseCase;

    @PostMapping
    public ResponseEntity<CreateArtistResponseDTO> create(@Valid @RequestBody CreateArtistRequestDTO artistRequestDTO) {
        var artistMapperToDomain = ArtistMapper.toDomain(artistRequestDTO);
        var result = createArtistUseCase.execute(artistMapperToDomain);

        return ResponseEntity.ok(ArtistPresenter.toResponse(result));
    }
}
