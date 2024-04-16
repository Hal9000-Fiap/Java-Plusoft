package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;

import java.time.LocalDateTime;

public record CustomerDetailsDTO(
        Long customerId,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {


    public CustomerDetailsDTO(Customer customer){
        this(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

}
