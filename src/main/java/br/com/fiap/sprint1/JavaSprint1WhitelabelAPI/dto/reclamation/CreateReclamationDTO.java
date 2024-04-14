package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationState;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateReclamationDTO(

        @NotBlank(message = "Titulo não pode ser vazio")
        @Size(max = 70, message = "Titulo pode ter no máximo 70 caracteres")
        String title,

        @NotBlank(message = "Texto não pode ser vazio")
        @Size(max = 250, message = "Texto pode ter no máximo 250 caracteres")
        String text,

        @NotBlank(message = "Tipo de Reclamação não pode ser vazio")
        @Size(max = 70, message = "Tipo de Reclamação pode ter no máximo 70 caracteres")
        ReclamationType type,

        @NotBlank(message = "Estado da reclamação não pode ser vazio")
        @Size(max = 30, message = "Estado da reclamação pode ter no máximo 30 caracteres")
        ReclamationState state

) {
}
