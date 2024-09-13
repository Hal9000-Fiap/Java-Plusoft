package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCustomerDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 ccaracteres")
        @Schema(description = "Nome do cliente", example = "João Silva")
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Size(max = 70, message = "Email pode ter no máximo 70 caracteres")
        @Schema(description = "Email do cliente", example = "joao.silva@email.com")
        String email
) {
}
