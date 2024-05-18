package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.SegmentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEnterpriseDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        String name,

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 14, min = 14, message = "CNPJ deve conter 14 caracteres")
        String cnpj,

        @NotBlank(message = "Tipo de segmento não pode ser vazio")
        @Size(max = 50, message = "Tipo de segmento pode ter no máximo 50 caracteres")
        SegmentType segmentType
){
}
