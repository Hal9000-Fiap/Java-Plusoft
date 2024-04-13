package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Reclamation;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationState;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationType;

import java.time.LocalDateTime;
import java.util.List;

public record ReclamationDetailsDTO(
        Long id,
        Long customerId,
        Long enterpriseId,
        String title,
        String text,
        ReclamationType type,
        ReclamationState state,
        List<EmployeeDetailsDTO> employees,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public ReclamationDetailsDTO(Reclamation reclamation){
        this(
                reclamation.getId(),
                reclamation.getCustomer().getId(),
                reclamation.getEnterprise().getId(),
                reclamation.getTitle(),
                reclamation.getText(),
                reclamation.getType(),
                reclamation.getState(),
                reclamation.getEmployees().stream().map(EmployeeDetailsDTO::new).toList(),
                reclamation.getCreatedAt(),
                reclamation.getUpdatedAt()
        );
    }

}
