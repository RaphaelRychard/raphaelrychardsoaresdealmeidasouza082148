package br.gov.mt.seplag.artistalbumapi.modules.auth.controller;

import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request.AuthenticationRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.LoginResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.useCases.AuthenticationUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationUseCase authenticationUseCase;

    @PostMapping("/login")
    @SecurityRequirement(name = "")
    public ResponseEntity<LoginResponseDTO> auth(@RequestBody AuthenticationRequestDTO data) {
        return ResponseEntity.ok(authenticationUseCase.execute(data));
    }
}
