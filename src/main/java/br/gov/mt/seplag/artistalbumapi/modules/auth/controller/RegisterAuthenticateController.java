package br.gov.mt.seplag.artistalbumapi.modules.auth.controller;

import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request.RegisterRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.RegisterResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.useCases.RegisterAuthenticateUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class RegisterAuthenticateController {

    @Autowired
    private RegisterAuthenticateUseCase registerAuthenticateUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> create(@Valid @RequestBody RegisterRequestDTO data) {
        var user = registerAuthenticateUseCase.execute(data.login(), data.password(), data.role());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponseDTO(user.getId()));
    }
}
