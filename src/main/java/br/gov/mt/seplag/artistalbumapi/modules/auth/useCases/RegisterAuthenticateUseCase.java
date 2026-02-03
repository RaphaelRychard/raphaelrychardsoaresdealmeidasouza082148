package br.gov.mt.seplag.artistalbumapi.modules.auth.useCases;

import br.gov.mt.seplag.artistalbumapi.exceptions.UserFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.User;
import br.gov.mt.seplag.artistalbumapi.modules.auth.enums.UserRole;
import br.gov.mt.seplag.artistalbumapi.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAuthenticateUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User execute(String login, String password, UserRole role) {
        if (userRepository.findByLogin(login) != null) {
            throw new UserFoundException();
        }

        String encryptedPassword = passwordEncoder.encode(password);

        User user = new User(login, encryptedPassword, role);

        return userRepository.save(user);
    }
}
