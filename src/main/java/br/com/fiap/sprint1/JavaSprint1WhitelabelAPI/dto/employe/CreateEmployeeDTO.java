package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEmployeeDTO (
        @NotBlank(message = "Nome não pode ser vazio")
        @Size(max = 50, message = "Email pode ter no máximo 50 caracteres")
        String name,

        @NotBlank(message = "Email não pode ser vazio")
        @Size(max = 70, message = "Email pode ter no máximo 70 caracteres")
        String email
){
}
