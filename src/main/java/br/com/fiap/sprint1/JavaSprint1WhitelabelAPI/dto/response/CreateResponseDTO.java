package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateResponseDTO(

        @NotBlank(message = "Mensagem não pode ser vazia")
        @Size(max = 250, message = "Mensagem pode ter no máximo 250 caracteres")
        @Schema(description = "Mensagem da resposta", example = "A sua reclamação foi registrada com sucesso.")
        String message
) {
}
