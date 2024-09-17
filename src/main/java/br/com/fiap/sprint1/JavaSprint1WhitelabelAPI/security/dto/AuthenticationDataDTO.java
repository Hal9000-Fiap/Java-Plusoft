package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationDataDTO(
        @NotBlank(message = "Email não pode ser vazio")
        @Size(max = 70, message = "Email pode ter no máximo 70 caracteres")
        @Schema(description = "Email do usuário", example = "joao.silva@email.com")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        @Size(max = 50, message = "Senha pode ter no máximo 50 ccaracteres")
        @Schema(description = "Senha do usuário", example = "@Password321")
        String password
) {
}
