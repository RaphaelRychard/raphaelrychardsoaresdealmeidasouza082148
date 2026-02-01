package br.gov.mt.seplag.artistalbumapi.modules.auth.mapper;

import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request.CreateUserRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toDomain(CreateUserRequestDTO dto) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
