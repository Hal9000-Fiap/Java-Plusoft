package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Response {

    @Id
    @GeneratedValue
    @Column(name = "response_id")
    private Long id;

    @Column(name = "commentary", nullable = false, length = 250)
    private String name;

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
