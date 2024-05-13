package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CreateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CustomerDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.UpdateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDetailsDTO> create(
            @RequestBody @Valid CreateCustomerDTO customerDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Customer customer = customerService.create(customerDTO);
        var url = uriBuilder.path("customers/{cudtomer_id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(url).body(new CustomerDetailsDTO(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDetailsDTO>> findAll(Pageable pageable) {
        var customerList = customerService.getAll(pageable);
        return ResponseEntity.ok(customerList);
    }

    @GetMapping({"/{cudtomer_id}"})
    public ResponseEntity<CustomerDetailsDTO> findOne(@PathVariable("cudtomer_id") Long customerId){
        var customer = customerService.getOne(customerId);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{cudtomer_id}")
    public ResponseEntity<CustomerDetailsDTO> update(
            @PathVariable("cudtomer_id") Long customerId,
            @RequestBody @Valid UpdateCustomerDTO customerDTO
    ){
        var customer = customerService.update(customerId, customerDTO);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{cudtomer_id}")
    public ResponseEntity<Void> delete(@PathVariable("cudtomer_id") Long customerId){
        customerService.delete(customerId);
        return ResponseEntity.noContent().build();
    }

}
