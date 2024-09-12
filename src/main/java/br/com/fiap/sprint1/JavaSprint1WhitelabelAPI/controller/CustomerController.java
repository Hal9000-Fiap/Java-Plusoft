package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CreateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.CustomerDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.customer.UpdateCustomerDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer", description = "CRUD actions to customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Register customer in application", description = "Register and create customer in database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully", content =
            @Content(schema = @Schema(implementation = CustomerDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @Parameters({
            @Parameter(name = "customerDTO", description = "Information to create a new customer", required = true)
    })
    public ResponseEntity<CustomerDetailsDTO> create(
            @RequestBody @Valid CreateCustomerDTO customerDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Customer customer = customerService.create(customerDTO);
        var url = uriBuilder.path("customers/{cudtomer_id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(url).body(new CustomerDetailsDTO(customer));
    }

    @GetMapping
    @Operation(summary = "List all customers", description = "Fetches a list of all customers in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of customers fetched successfully", content =
            @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerDetailsDTO.class)), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<List<CustomerDetailsDTO>> findAll() {
        var customerList = customerService.getAll();
        return ResponseEntity.ok(customerList);
    }

    @GetMapping({"{cudtomer_id}"})
    @Operation(summary = "Fetch customer by ID", description = "Fetch a specific customer from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer fetched successfully", content =
            @Content(schema = @Schema(implementation = CustomerDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Customer not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @Parameters({
            @Parameter(name = "customer_id", description = "ID of the customer to be fetched", required = true)
    })
    public ResponseEntity<CustomerDetailsDTO> findOne(@PathVariable("cudtomer_id") Long customerId){
        var customer = customerService.getOne(customerId);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("{cudtomer_id}")
    @Operation(summary = "Update customer details", description = "Update a specific customer's details by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully", content =
            @Content(schema = @Schema(implementation = CustomerDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Customer not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid data")
    })
    @Parameters({
            @Parameter(name = "customer_id", description = "ID of the customer to be updated", required = true),
            @Parameter(name = "customerDTO", description = "Updated customer data", required = true)
    })
    public ResponseEntity<CustomerDetailsDTO> update(
            @PathVariable("cudtomer_id") Long customerId,
            @RequestBody @Valid UpdateCustomerDTO customerDTO
    ){
        var customer = customerService.update(customerId, customerDTO);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("{cudtomer_id}")
    @Operation(summary = "Delete customer", description = "Delete a customer from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @Parameters({
            @Parameter(name = "customer_id", description = "ID of the customer to be deleted", required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("cudtomer_id") Long customerId){
        customerService.delete(customerId);
        return ResponseEntity.noContent().build();
    }

}
