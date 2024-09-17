package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Informações para cadastro de cliente")
public record CreateCustomerDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 ccaracteres")
        @Schema(description = "Nome do cliente", example = "João Silva")
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Size(max = 70, message = "Email pode ter no máximo 70 caracteres")
        @Schema(description = "Email do cliente", example = "joao.silva@email.com")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        @Size(max = 100, message = "Senha pode ter no máximo 100 ccaracteres")
        @Schema(description = "Senha do cliente", example = "@Password321")
        String password
) {
}
