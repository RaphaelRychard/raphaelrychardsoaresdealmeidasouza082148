package br.gov.mt.seplag.artistalbumapi.modules.auth.controller;

import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request.CreateUserRequestDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.dto.response.CreateUserResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.auth.mapper.UserMapper;
import br.gov.mt.seplag.artistalbumapi.modules.auth.presenter.UserPresenter;
import br.gov.mt.seplag.artistalbumapi.modules.auth.useCases.CreateUserUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "users")
public class CreateUserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> create(@Valid @RequestBody CreateUserRequestDTO userRequest) {
        var userEntityToDomain = userMapper.toDomain(userRequest);
        var result = createUserUseCase.execute(userEntityToDomain);

        return ResponseEntity.ok(UserPresenter.toResponse(result));
    }
}
