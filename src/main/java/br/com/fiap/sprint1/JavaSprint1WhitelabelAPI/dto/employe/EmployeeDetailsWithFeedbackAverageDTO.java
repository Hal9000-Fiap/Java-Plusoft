package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;


public record EmployeeDetailsWithFeedbackAverageDTO(
        EmployeeDetailsDTO employeeDetails,
        Double feedbackAvarage
) {
    public EmployeeDetailsWithFeedbackAverageDTO(Employee employee, Double feedbackAvarage) {
        this(
                new EmployeeDetailsDTO(employee),
                feedbackAvarage
        );
    }
}
