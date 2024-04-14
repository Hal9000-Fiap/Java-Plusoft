package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback;

import jakarta.validation.constraints.*;

public record CreateServiceFeedbackDTO(

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Email pode ter no máximo 50 caracteres")
        String name,

        @NotEmpty(message = "Nota do atendimento não pode ser vazia")
        @Min(value = 0, message = "Nota do atendimento deve ser no mínimo 0")
        @Max(value = 5, message = "Nota do atendimento deve ser no máximo 5")
        Double rating

) {
}
