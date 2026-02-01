package br.gov.mt.seplag.artistalbumapi.modules.auth.useCases;

import br.gov.mt.seplag.artistalbumapi.exceptions.UserFoundException;
import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.UserEntity;
import br.gov.mt.seplag.artistalbumapi.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserEntity execute(UserEntity userEntity) {
        this.userRepository.findByUsername(userEntity.getUsername())
            .ifPresent(user -> {
                throw new UserFoundException();
            });

        var passwordEncode = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(passwordEncode);

        return this.userRepository.save(userEntity);
    }
}
