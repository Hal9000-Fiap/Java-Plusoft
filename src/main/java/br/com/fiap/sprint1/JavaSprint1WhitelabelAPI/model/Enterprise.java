package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.SegmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "JV_SPRINT1_ENTERṔRISE")
public class Enterprise {

    @Id
    @GeneratedValue
    @Column(name = "enterprise_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "cnpj", nullable = false, length = 11)
    private String cnpj;

    @Column(name = "segment_type", nullable = false, length = 50)
    private SegmentType segmentType;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
