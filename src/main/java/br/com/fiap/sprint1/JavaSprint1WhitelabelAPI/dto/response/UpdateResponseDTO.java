package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateResponseDTO(
        @NotBlank(message = "Mensagem não pode ser vazia")
        @Size(max = 250, message = "Mensagem pode ter no máximo 250 caracteres")
        String message
) {
}
