package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.SegmentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEnterpriseDTO(

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        @Schema(description = "Nome atualizado da empresa", example = "Tech Solutions")
        String name,

        @NotBlank(message = "Tipo de segmento não pode ser vazio")
        @Size(max = 50, message = "Tipo de segmento pode ter no máximo 50 caracteres")
        @Schema(description = "Segmento atualizado da empresa", example = "Tecnologia")
        SegmentType segmentType

){
}
