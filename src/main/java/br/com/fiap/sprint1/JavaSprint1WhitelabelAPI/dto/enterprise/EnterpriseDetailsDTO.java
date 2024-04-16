package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Enterprise;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.SegmentType;

import java.time.LocalDateTime;

public record EnterpriseDetailsDTO(
        Long enterpriseId,
        String name,
        String cnpj,
        SegmentType segmentType,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {

    public EnterpriseDetailsDTO(Enterprise enterprise){
        this(
                enterprise.getId(),
                enterprise.getName(),
                enterprise.getCnpj(),
                enterprise.getSegmentType(),
                enterprise.getCreatedAt(),
                enterprise.getUpdatedAt()
        );
    }

}
