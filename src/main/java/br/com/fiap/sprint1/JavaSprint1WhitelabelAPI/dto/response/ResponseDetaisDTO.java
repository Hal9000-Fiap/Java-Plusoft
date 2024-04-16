package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Response;

import java.time.LocalDateTime;

public record ResponseDetaisDTO(
        Long responseId,
        Long reclamationId,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {

    public ResponseDetaisDTO(Response response) {
        this(
            response.getId(),
            response.getReclamation().getId(),
            response.getMessage(),
            response.getCreatedAt(),
            response.getUpdatedAt()
        );
    }

}
