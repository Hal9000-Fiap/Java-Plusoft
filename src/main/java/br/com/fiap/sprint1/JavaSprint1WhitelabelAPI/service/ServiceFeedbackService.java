package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.CreateServiceFeedbackDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.ServiceFeedbackDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.UpdateServiceFeedbackDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.handler.InvalidReferenceException;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Employee;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.EmployeeRepository;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.ServiceFeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceFeedbackService {

    @Autowired
    ServiceFeedbackRepository serviceFeedbackRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public ServiceFeedBack create(Long employeeId, CreateServiceFeedbackDTO serviceFeedbackDTO) {
        Employee employee = employeeRepository.getReferenceById(employeeId);
        ServiceFeedBack serviceFeedBack = new ServiceFeedBack(serviceFeedbackDTO);
        serviceFeedBack.setEmployee(employee);

        return serviceFeedbackRepository.save(serviceFeedBack);
    }

    public List<ServiceFeedbackDetailsDTO> getAll(Pageable pageable) {
        return serviceFeedbackRepository.findAll(pageable)
                .stream().map(ServiceFeedbackDetailsDTO::new).toList();
    }

    public ServiceFeedbackDetailsDTO getOne(Long serviceFeedbackId) {
        ServiceFeedBack serviceFeedBack = serviceFeedbackRepository.getReferenceById(serviceFeedbackId);

        return new ServiceFeedbackDetailsDTO(serviceFeedBack);
    }

    public List<ServiceFeedbackDetailsDTO> getAllFeedbacksByEmployee(Long employeeId) {
        Employee employee = employeeRepository.getReferenceById(employeeId);
        var feedbackList = employee.getServiceFeedBacks();
        return feedbackList.stream().map(ServiceFeedbackDetailsDTO::new).toList();
    }

    @Transactional
    public ServiceFeedbackDetailsDTO update(
            Long serviceFeedbackId,
            Long employeeId,
            UpdateServiceFeedbackDTO serviceFeedbackDTO
    ) {
        Employee employee = employeeRepository.getReferenceById(employeeId);
        ServiceFeedBack serviceFeedBack = serviceFeedbackRepository.getReferenceById(serviceFeedbackId);

        var employeeFeedbacksIds = employee.getServiceFeedBacks().stream()
                .map(ServiceFeedBack::getId).collect(Collectors.toList());

        if(!employeeFeedbacksIds.contains(serviceFeedbackId))
            throw new InvalidReferenceException();

        serviceFeedBack.setCommentary(serviceFeedbackDTO.commentary());
        serviceFeedBack.setRating(serviceFeedbackDTO.rating());
        serviceFeedBack.setUpdatedAt(LocalDateTime.now());
        serviceFeedbackRepository.save(serviceFeedBack);

        return new ServiceFeedbackDetailsDTO(serviceFeedBack);
    }

    @Transactional
    public void delete(Long serviceFeedbackId) {
        serviceFeedbackRepository.deleteById(serviceFeedbackId);
    }

}
