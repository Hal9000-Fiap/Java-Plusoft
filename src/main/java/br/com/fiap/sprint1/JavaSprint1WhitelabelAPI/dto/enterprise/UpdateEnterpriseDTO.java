package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.SegmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEnterpriseDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        String name,

        SegmentType segmentType
){
}
