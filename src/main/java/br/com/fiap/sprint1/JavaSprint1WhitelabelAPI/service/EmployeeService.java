package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.CreateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.EmployeeDetailsWithFeedbackAverageDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.employe.UpdateEmployeeDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public EmployeeDetailsWithFeedbackAverageDTO getAverageEmployeeFeedback(Long employeeId){
        Employee employee = employeeRepository.getReferenceById(employeeId);
        List<BigDecimal> ratingList = employee.getServiceFeedBacks().stream().map(ServiceFeedBack::getRating).toList();
        BigDecimal sum = ratingList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = ratingList.isEmpty() ?
                BigDecimal.ZERO :
                sum.divide(BigDecimal.valueOf(ratingList.size()), 2, BigDecimal.ROUND_HALF_UP);

        return new EmployeeDetailsWithFeedbackAverageDTO(employee, average);
    }

    @Transactional
    public EmployeeDetailsDTO update(Long employeeId, UpdateEmployeeDTO employeeDTO){
        Employee employee = employeeRepository.getReferenceById(employeeId);
        employee.setName(employeeDTO.name());
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
