package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.CreateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsWithFeedbackAverageDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.UpdateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDetailsDTO> create(@RequestBody @Valid CreateEmployeeDTO employeeDTO,
                                                     UriComponentsBuilder uriBuilder) {
        Employee employee = employeeService.create(employeeDTO);
        var url = uriBuilder.path("employees/{employee_id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(url).body(new EmployeeDetailsDTO(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDetailsDTO>> findAll(Pageable pageable) {
        var employeeList = employeeService.getAll(pageable);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping({"/{employee_id}"})
    public ResponseEntity<EmployeeDetailsDTO> findOne(@PathVariable("employee_id") Long employeeId){
        var employee = employeeService.getOne(employeeId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping({"/{employee_id}/feedback-avarage"})
    public ResponseEntity<EmployeeDetailsWithFeedbackAverageDTO> getAverageEmployeeFeedback(@PathVariable("employee_id") Long employeeId){
        var employee = employeeService.getAverageEmployeeFeedback(employeeId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{employee_id}")
    public ResponseEntity<EmployeeDetailsDTO> update(@PathVariable("employee_id") Long employeeId,
                                                     @RequestBody @Valid UpdateEmployeeDTO employeeDTO){
        var employee = employeeService.update(employeeId, employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{employee_id}")
    public ResponseEntity<Void> delete(@PathVariable("employee_id") Long employeeId){
        employeeService.delete(employeeId);
        return ResponseEntity.noContent().build();
    }

}
