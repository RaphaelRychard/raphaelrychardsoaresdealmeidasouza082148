package br.gov.mt.seplag.artistalbumapi.providers;

import br.gov.mt.seplag.artistalbumapi.exceptions.AuthenticationUserException;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.LoginResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secret;

    public LoginResponseDTO generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Instant expiration = this.genExpirationDate();

            String token = JWT.create()
                    .withIssuer("auth-api-seplag-artist-album")
                    .withSubject(user.getLogin())
                    .withExpiresAt(this.genExpirationDate())
                    .sign(algorithm);

            return new LoginResponseDTO(token, expiration);
        } catch (JWTCreationException exception) {
            throw new AuthenticationUserException();
        }
    }

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("auth-api-seplag-artist-album")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new AuthenticationUserException();
        }
    }

    private Instant genExpirationDate() {
        return Instant.now().plus(Duration.ofMinutes(5));
    }
}
