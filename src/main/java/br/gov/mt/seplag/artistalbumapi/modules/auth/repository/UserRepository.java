package br.gov.mt.seplag.artistalbumapi.modules.auth.repository;

import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
