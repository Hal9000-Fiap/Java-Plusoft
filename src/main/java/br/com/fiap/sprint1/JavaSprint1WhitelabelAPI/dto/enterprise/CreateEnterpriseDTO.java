package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.SegmentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEnterpriseDTO(

        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Nome pode ter no máximo 50 caracteres")
        @Schema(description = "Nome da empresa", example = "Tech Solutions")
        String name,

        @NotBlank(message = "CNPJ não pode ser vazio")
        @Size(min = 14, max = 14, message = "CNPJ deve conter 14 caracteres")
        @Schema(description = "CNPJ da empresa", example = "12345678000199")
        String cnpj,

        @NotBlank(message = "Tipo de segmento não pode ser vazio")
        @Size(max = 50, message = "Tipo de segmento pode ter no máximo 50 caracteres")
        @Schema(description = "Segmento da empresa", example = "Tecnologia")
        SegmentType segmentType

){
}
