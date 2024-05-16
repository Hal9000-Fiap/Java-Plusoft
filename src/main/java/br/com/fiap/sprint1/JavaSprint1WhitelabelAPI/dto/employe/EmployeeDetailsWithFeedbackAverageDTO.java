package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;

import java.math.BigDecimal;

public record EmployeeDetailsWithFeedbackAverageDTO(
        EmployeeDetailsDTO employeeDetails,
        BigDecimal feedbackAvarage
) {
    public EmployeeDetailsWithFeedbackAverageDTO(Employee employee, BigDecimal feedbackAvarage) {
        this(
                new EmployeeDetailsDTO(employee),
                feedbackAvarage
        );
    }
}
