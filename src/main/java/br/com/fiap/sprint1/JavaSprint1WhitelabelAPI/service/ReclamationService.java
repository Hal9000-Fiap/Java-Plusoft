package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.CreateReclamationDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.ReclamationDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.UpdateServiceFeedbackDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Enterprise;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Reclamation;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.CustomerRepository;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.EmployeeRepository;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.EnterpriseRepository;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.ReclamationRepositry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReclamationService {

    @Autowired
    ReclamationRepositry reclamationRepositry;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Transactional
    public Reclamation create(Long customerId,
                              Long enterpriseId,
                              CreateReclamationDTO reclamationDTO){
        Reclamation reclamation = new Reclamation(reclamationDTO);
        Customer customer = customerRepository.getReferenceById(customerId);
        Enterprise enterprise = enterpriseRepository.getReferenceById(enterpriseId);
        Set<Employee> employees = new HashSet<>();

        for (Long employeeId : reclamationDTO.employeeIds()) {
            Employee employee = employeeRepository.getReferenceById(employeeId);
            employees.add(employee);
        }

        reclamation.setCustomer(customer);
        reclamation.setEmployees(employees);
        reclamation.setEnterprise(enterprise);

        return reclamationRepositry.save(reclamation);
    }

    public List<ReclamationDetailsDTO> getAll(Pageable pageable) {
        return reclamationRepositry.findAll(pageable)
                .stream().map(ReclamationDetailsDTO::new).toList();
    }

    public ReclamationDetailsDTO getOne(Long reclamationId) {
        Reclamation reclamation = reclamationRepositry.getReferenceById(reclamationId);

        return new ReclamationDetailsDTO(reclamation);
    }

    @Transactional
    public void delete(Long reclamationId) {
        reclamationRepositry.deleteById(reclamationId);
    }

}
