package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.CreateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.UpdateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@SecurityRequirement(name = "bearerJWT")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Create a new employee", description = "Register a new employee in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Employee created successfully", content =
            @Content(schema = @Schema(implementation = EmployeeDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid data", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    public ResponseEntity<EmployeeDetailsDTO> create(
            @RequestBody @Valid CreateEmployeeDTO employeeDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Employee employee = employeeService.create(employeeDTO);
        var url = uriBuilder.path("employees/{employee_id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(url).body(new EmployeeDetailsDTO(employee));
    }

    @GetMapping
    @Operation(summary = "Fetch all employees", description = "Retrieve a list of all employees from the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employees fetched successfully", content =
            @Content(schema = @Schema(implementation = EmployeeDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    public ResponseEntity<List<EmployeeDetailsDTO>> findAll(Pageable page) {
        var employeeList = employeeService.getAll(page);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping({"{employee_id}"})
    @Operation(summary = "Fetch employee by ID", description = "Retrieve a specific employee from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee fetched successfully", content =
            @Content(schema = @Schema(implementation = EmployeeDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Employee not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "employee_id", description = "ID of the employee to be fetched", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<EmployeeDetailsDTO> findOne(@PathVariable("employee_id") Long employeeId){
        var employee = employeeService.getOne(employeeId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("{employee_id}")
    @Operation(summary = "Update employee by ID", description = "Update the details of an existing employee by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully", content =
            @Content(schema = @Schema(implementation = EmployeeDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Employee not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid data", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "employee_id", description = "ID of the employee to be fetched", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<EmployeeDetailsDTO> update(
            @PathVariable("employee_id") Long employeeId,
            @RequestBody @Valid UpdateEmployeeDTO employeeDTO
    ){
        var employee = employeeService.update(employeeId, employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("{employee_id}")
    @Operation(summary = "Delete employee by ID", description = "Remove an employee from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Employee not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "employee_id", description = "ID of the employee to be fetched", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(@PathVariable("employee_id") Long employeeId){
        employeeService.delete(employeeId);
        return ResponseEntity.noContent().build();
    }

}
