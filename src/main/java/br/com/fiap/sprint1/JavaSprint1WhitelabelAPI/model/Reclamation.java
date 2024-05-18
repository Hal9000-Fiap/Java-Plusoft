package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.CreateReclamationDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationState;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.enums.ReclamationType;
import jakarta.persistence.*;
import jakarta.websocket.MessageHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "JV_SPRINT1_RECLAMATION")
@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    private Enterprise enterprise;

    @OneToMany(mappedBy = "reclamation", fetch = FetchType.LAZY)
    private List<Response> responses;

    @ManyToMany(mappedBy = "reclamations", fetch = FetchType.EAGER)
    private Set<Employee> employees;

    public Reclamation(CreateReclamationDTO reclamationDTO) {
        title = reclamationDTO.title();
        text = reclamationDTO.text();
        type = reclamationDTO.type();
        state = reclamationDTO.state();
    }

}