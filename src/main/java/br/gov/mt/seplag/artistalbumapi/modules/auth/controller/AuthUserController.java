package br.gov.mt.seplag.artistalbumapi.modules.auth.controller;

import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request.AuthUserRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.AuthUserResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.useCases.AuthUserUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "auth")
public class AuthUserController {

    @Autowired
    private AuthUserUseCase authUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponseDTO> auth(@RequestBody AuthUserRequestDTO authUserRequestDTO) {
        return ResponseEntity.ok(this.authUserUseCase.execute(authUserRequestDTO));
    }
}
