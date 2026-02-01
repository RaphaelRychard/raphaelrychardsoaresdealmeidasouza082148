package br.gov.mt.seplag.artistalbumapi.modules.auth.useCases;

import br.gov.mt.seplag.artistalbumapi.exceptions.AuthenticationUserException;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request.AuthUserRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.AuthUserResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthUserUseCase {

    @Value("${security.token.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUserResponseDTO execute(AuthUserRequestDTO authUserDTO)  {
        var user = this.userRepository.findByUsername(authUserDTO.getUsername())
                .orElseThrow(AuthenticationUserException::new);

        var passwordMatches = this.passwordEncoder.matches(
                authUserDTO.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new AuthenticationUserException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secret);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(5));

        var token = JWT.create()
                .withIssuer("seplagartistalbum")
                .withSubject(user.getId().toString())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthUserResponseDTO
                .builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
