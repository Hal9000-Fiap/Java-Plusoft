package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateServiceFeedbackDTO(
        @NotBlank(message = "Comentário não pode ser vazio")
        @Size(max = 250, message = "Comentário pode ter no máximo 250 caracteres")
        String commentary,

        @NotEmpty(message = "Nota do atendimento não pode ser vazia")
        @Min(value = 0, message = "Nota do atendimento deve ser no mínimo 0")
        @Max(value = 5, message = "Nota do atendimento deve ser no máximo 5")
        BigDecimal rating
) {
}
