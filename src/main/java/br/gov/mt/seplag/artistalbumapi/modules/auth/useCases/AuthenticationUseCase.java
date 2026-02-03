package br.gov.mt.seplag.artistalbumapi.modules.auth.useCases;

import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request.AuthenticationRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.LoginResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.User;
import br.gov.mt.seplag.artistalbumapi.providers.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final JWTProvider jwtProvider;

    public LoginResponseDTO execute(AuthenticationRequestDTO data) {

        var usernamePassword =
                new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var authentication = authenticationManager.authenticate(usernamePassword);

        User user = (User) authentication.getPrincipal();

        var token = jwtProvider.generateToken(user);

        return new LoginResponseDTO(
                token.getAccessToken(),
                token.getExpiresIn()
        );
    }
}
