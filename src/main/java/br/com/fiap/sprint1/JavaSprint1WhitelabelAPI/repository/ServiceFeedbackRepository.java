package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceFeedbackRepository extends JpaRepository<ServiceFeedBack, Long> {

    @Query("select avg(sf.rating) from ServiceFeedBack sf where sf.employee.id = :employee_id")
    Double feedbackAvaregeByEmployee(@Param("employee_id") Long employeeId);

}
