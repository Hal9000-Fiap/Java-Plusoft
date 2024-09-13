package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record ServiceFeedbackDetailsDTO(
        Long serviceFeedbackId,
        Long employeeId,
        String commentary,
        BigDecimal rating,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {

    public ServiceFeedbackDetailsDTO(ServiceFeedBack serviceFeedBack) {
        this(
                serviceFeedBack.getId(),
                serviceFeedBack.getEmployee().getId(),
                serviceFeedBack.getCommentary(),
                serviceFeedBack.getRating(),
                serviceFeedBack.getCreatedAt(),
                serviceFeedBack.getUpdatedAt()
        );
    }

}
