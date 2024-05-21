package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CreateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CustomerDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.UpdateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer create(CreateCustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO);

        return customerRepository.save(customer);
    }

    public List<CustomerDetailsDTO> getAll(Pageable pageable){
        return customerRepository.findAll(pageable)
                .stream().map(CustomerDetailsDTO::new).toList();
    }

    public CustomerDetailsDTO getOne(Long customerId){
        Customer customer = customerRepository.getReferenceById(customerId);
        return new CustomerDetailsDTO(customer);
    }

    @Transactional
    public CustomerDetailsDTO update(Long customerId, UpdateCustomerDTO customerDTO){
        Customer customer = customerRepository.getReferenceById(customerId);
        customer.setName(customerDTO.name());
        customer.setEmail(customerDTO.email());
        customer.setUpdatedAt(LocalDateTime.now());
        customerRepository.save(customer);

        return new CustomerDetailsDTO(customer);
    }


    @Transactional
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

}
