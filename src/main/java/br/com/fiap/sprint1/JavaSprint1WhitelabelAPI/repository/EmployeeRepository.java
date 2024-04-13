package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
