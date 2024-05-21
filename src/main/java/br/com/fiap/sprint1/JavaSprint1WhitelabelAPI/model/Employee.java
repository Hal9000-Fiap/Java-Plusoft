package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.CreateEmployeeDTO;
import jakarta.persistence.*;
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
@Table(name = "JV_SPRINT1_EMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 70, unique = true)
    private String email;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<ServiceFeedBack> serviceFeedBacks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "JV_SPRINT1_RECLATION_EMPLOYEE",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "reclamation_id"))
    private Set<Reclamation> reclamations;

    public Employee(CreateEmployeeDTO employeeDTO) {
        name = employeeDTO.name();
        email = employeeDTO.email();
    }

}
