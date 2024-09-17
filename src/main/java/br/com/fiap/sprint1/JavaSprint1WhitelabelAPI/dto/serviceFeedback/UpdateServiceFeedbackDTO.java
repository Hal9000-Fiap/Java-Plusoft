package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(description = "Informações para atualização de feedback de serviços presatadps")
public record UpdateServiceFeedbackDTO(
        @NotBlank(message = "Comentário não pode ser vazio")
        @Size(max = 250, message = "Comentário pode ter no máximo 250 caracteres")
        @Schema(description = "Comentário atualizado sobre o serviço", example = "Atendimento melhorou muito.")
        String commentary,

        @NotEmpty(message = "Nota do atendimento não pode ser vazia")
        @Min(value = 0, message = "Nota do atendimento deve ser no mínimo 0")
        @Max(value = 5, message = "Nota do atendimento deve ser no máximo 5")
        @Schema(description = "Nota atualizada do atendimento", example = "5.0")
        BigDecimal rating
) {
}
