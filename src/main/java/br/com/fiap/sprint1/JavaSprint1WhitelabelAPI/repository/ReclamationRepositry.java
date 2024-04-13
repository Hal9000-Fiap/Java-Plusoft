package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepositry extends JpaRepository<Reclamation, Long> {
}
