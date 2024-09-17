package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Customer c where LOWER(c.email) = LOWER(:email)")
    Customer findByEmail(String email);
}
