package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationState;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "Informações para atualização de reclamações")
public record UpdateReclamationDTO(

        @NotBlank(message = "Título não pode ser vazio")
        @Size(max = 70, message = "Título pode ter no máximo 70 caracteres")
        @Schema(description = "Título atualizado da reclamação", example = "Problema com entrega")
        String title,

        @NotBlank(message = "Texto não pode ser vazio")
        @Size(max = 250, message = "Texto pode ter no máximo 250 caracteres")
        @Schema(description = "Texto atualizado da reclamação", example = "A entrega atrasou mais de 3 dias.")
        String text,

        @NotNull(message = "Tipo de Reclamação não pode ser vazio")
        @Schema(description = "Tipo atualizado da reclamação", example = "Atraso")
        ReclamationType type,

        @NotNull(message = "Estado da reclamação não pode ser vazio")
        @Schema(description = "Estado atualizado da reclamação", example = "Concluída")
        ReclamationState state,

        @NotBlank(message = "Lista de IDs de funcionários não pode ser vazia")
        @Schema(description = "Lista de IDs atualizados dos funcionários responsáveis", example = "[1, 2, 3]")
        List<Long> employeeIds

) {
}
