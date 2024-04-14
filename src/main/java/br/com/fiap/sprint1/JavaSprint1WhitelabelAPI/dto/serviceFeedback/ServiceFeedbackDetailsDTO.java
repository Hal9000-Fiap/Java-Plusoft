package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceFeedbackDetailsDTO(

        Long serviceFeedbackId,
        String name,
        BigDecimal rating,
        LocalDateTime createdAt,
        LocalDateTime updateAt,
        Employee employee

) {

    public ServiceFeedbackDetailsDTO(ServiceFeedBack serviceFeedBack) {
        this(
                serviceFeedBack.getId(),
                serviceFeedBack.getName(),
                serviceFeedBack.getRating(),
                serviceFeedBack.getCreatedAt(),
                serviceFeedBack.getUpdatedAt(),
                serviceFeedBack.getEmployee()
        );
    }

}
