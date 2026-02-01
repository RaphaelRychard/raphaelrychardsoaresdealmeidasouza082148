package br.gov.mt.seplag.artistalbumapi.modules.auth.presenter;

import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.CreateUserResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.entity.UserEntity;

public class UserPresenter {
    public static CreateUserResponseDTO toResponse(UserEntity entity) {
        return CreateUserResponseDTO.builder()
                .userId(entity.getId())
                .build();
    }
}
