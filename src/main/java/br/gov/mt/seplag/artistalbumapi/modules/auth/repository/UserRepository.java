package br.gov.mt.seplag.artistalbumapi.modules.auth.repository;

import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
