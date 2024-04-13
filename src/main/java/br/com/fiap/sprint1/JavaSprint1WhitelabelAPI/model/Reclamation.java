package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationState;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "JV_SPRINT1_RECLAMATION")
public class Reclamation {

    @Id
    @GeneratedValue
    @Column(name = "reclamation_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 70)
    private String title;

    @Column(name = "text", nullable = false, length = 250)
    private String text;

    @Column(name = "reclamation_type", nullable = false, length = 70)
    private ReclamationType type;

    @Column(name = "reclamation_state", nullable = false, length = 30)
    private ReclamationState state;

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