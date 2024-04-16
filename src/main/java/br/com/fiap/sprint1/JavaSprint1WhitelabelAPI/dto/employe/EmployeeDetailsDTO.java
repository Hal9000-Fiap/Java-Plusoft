package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;

import java.time.LocalDateTime;

public record EmployeeDetailsDTO(
        Long employeeId,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {

    public EmployeeDetailsDTO(Employee employee){
        this(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getCreatedAt(),
                employee.getUpdatedAt()
        );
    }

}
