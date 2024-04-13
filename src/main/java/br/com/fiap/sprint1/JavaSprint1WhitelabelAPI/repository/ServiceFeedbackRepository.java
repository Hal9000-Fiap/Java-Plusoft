package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceFeedbackRepository extends JpaRepository<ServiceFeedBack, Long> {
}
