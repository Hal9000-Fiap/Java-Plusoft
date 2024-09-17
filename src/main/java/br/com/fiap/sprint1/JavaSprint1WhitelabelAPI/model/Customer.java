package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CreateCustomerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "JV_SPRINT1_CUSTOMER")
@EntityListeners(AuditingEntityListener.class)
public class Customer implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 70, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 500, unique = true)
    private String password;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Reclamation> reclamations;

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
