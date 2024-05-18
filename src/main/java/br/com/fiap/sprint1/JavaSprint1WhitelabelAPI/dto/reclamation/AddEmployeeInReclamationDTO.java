package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddEmployeeInReclamationDTO(
        @NotNull(message = "Lista de Ids de funcionários não pode ser vazia")
        List<Long> employeeIds
) {}
