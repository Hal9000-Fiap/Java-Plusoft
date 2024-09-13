package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEmployeeDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        @Schema(description = "Nome atualizado do funcionário", example = "Maria Oliveira")
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Size(max = 70, message = "Email pode ter no máximo 70 caracteres")
        @Schema(description = "Email atualizado do funcionário", example = "maria.oliveira@email.com")
        String email
) {
}