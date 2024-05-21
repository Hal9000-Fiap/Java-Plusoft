package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationState;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateReclamationDTO(
        @NotBlank(message = "Titulo não pode ser vazio")
        @Size(max = 70, message = "Titulo pode ter no máximo 70 caracteres")
        String title,

        @NotBlank(message = "Texto não pode ser vazio")
        @Size(max = 250, message = "Texto pode ter no máximo 250 caracteres")
        String text,

        @NotNull(message = "Tipo de Reclamação não pode ser vazio")
        ReclamationType type,

        @NotNull(message = "Estado da reclamação não pode ser vazio")
        ReclamationState state,

        @NotNull(message = "Lista de Ids de funcionários não pode ser vazia")
        List<Long> employeeIds
) {
}
