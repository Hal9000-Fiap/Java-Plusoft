package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.CreateServiceFeedbackDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter@Setter
@NoArgsConstructor

@Entity
@Table(name="JV_SPRINT1_SERVICE_FEEDBACK")
public class ServiceFeedBack {

    @Id
    @GeneratedValue
    @Column(name = "service_feedback_id")
    private Long id;

    @Column(name = "commentary", nullable = false, length = 250)
    private String commentary;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public ServiceFeedBack(CreateServiceFeedbackDTO serviceFeedbackDTO) {
        commentary = serviceFeedbackDTO.commentary();
        rating = serviceFeedbackDTO.rating();
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
