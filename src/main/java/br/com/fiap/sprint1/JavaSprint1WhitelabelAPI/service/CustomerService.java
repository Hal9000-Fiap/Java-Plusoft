package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CreateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CustomerDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.UpdateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Customer create(CreateCustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.name(), customerDTO.email(), passwordEncoder.encode(customerDTO.password()));

        return customerRepository.save(customer);
    }

    public List<CustomerDetailsDTO> getAll(){
        List<CustomerDetailsDTO> customerList = customerRepository.findAll()
                .stream().map(CustomerDetailsDTO::new).toList();;
        return customerList;
    }

    public CustomerDetailsDTO getOne(Long customerId){
        Customer customer = customerRepository.getReferenceById(customerId);
        return new CustomerDetailsDTO(customer);
    }

    @Transactional
    public CustomerDetailsDTO update(Long customerId, UpdateCustomerDTO customerDTO){
        Customer customer = customerRepository.getReferenceById(customerId);

        if(customer.getName() != null)
            customer.setName(customerDTO.name());

        if(customer.getEmail() != null)
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
