package br.gov.mt.seplag.artistalbumapi.modules.album.controller;

import br.gov.mt.seplag.artistalbumapi.modules.album.dto.response.AlbumResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.album.presenter.AlbumPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.album.useCases.GetAllAlbumsUseCase;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.shared.dto.SimplePageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
@SecurityRequirement(name = "jwt_auth")
@Tag(name = "Albums")
public class GetAllAlbumsController {

    @Autowired
    private GetAllAlbumsUseCase getAllAlbumsUseCase;

    @Operation(summary = "Listar todos os álbuns com paginação")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Lista de álbuns retornada")})
    @GetMapping
    public ResponseEntity<SimplePageResponseDTO<AlbumResponseDTO>> getAllAlbums(@Valid @ParameterObject SimplePageRequestDTO request) {
        var result = getAllAlbumsUseCase.execute(request.toPageable());
        return ResponseEntity.ok(AlbumPresenter.toPageResponse(result));
    }
}
