package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.CreateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.UpdateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO){
        Employee employee = new Employee(employeeDTO);

        return employeeRepository.save(employee);
    }

    public List<EmployeeDetailsDTO> getAll(Pageable pageable){
        return employeeRepository.findAll(pageable)
                .stream().map(EmployeeDetailsDTO::new).toList();
    }

    public EmployeeDetailsDTO getOne(Long employeeId){
        Employee employee = employeeRepository.getReferenceById(employeeId);
        return new EmployeeDetailsDTO(employee);
    }

    @Transactional
    public EmployeeDetailsDTO update(Long employeeId, UpdateEmployeeDTO employeeDTO){
        Employee employee = employeeRepository.getReferenceById(employeeId);

        if(employee.getName() != null)
            employee.setName(employeeDTO.name());

        if(employee.getEmail() != null)
            employee.setEmail(employeeDTO.email());

        employee.setUpdatedAt(LocalDateTime.now());

        employeeRepository.save(employee);

        return new EmployeeDetailsDTO(employee);
    }


    @Transactional
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

}
