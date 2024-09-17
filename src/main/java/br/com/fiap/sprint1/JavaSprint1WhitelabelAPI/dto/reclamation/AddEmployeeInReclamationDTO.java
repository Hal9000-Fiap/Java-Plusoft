package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Informações para adicionar funcionários a uma reclamação")
public record AddEmployeeInReclamationDTO(
        @NotNull(message = "Lista de Ids de funcionários não pode ser vazia")
        List<Long> employeeIds
) {}
