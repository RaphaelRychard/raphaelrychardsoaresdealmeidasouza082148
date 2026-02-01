package br.gov.mt.seplag.artistalbumapi.modules.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Dados para criação de usuário")
public class CreateUserRequestDTO {

    @Schema(example = "João Silva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "Username é obrigatório")
    @Pattern(
            regexp = "^\\S+$",
            message = "O campo [username] não deve conter espaços"
    )
    @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    @Schema(example = "jaosilva", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 10, max = 100, message = "Senha deve ter entre 10 e 100 caracteres")
    @Schema(example = "senhaSegura123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
