package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.CreateServiceFeedbackDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.ServiceFeedbackDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.UpdateServiceFeedbackDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.ServiceFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/servicefeedbacks")
public class ServiceFeedbackController {

    @Autowired
    ServiceFeedbackService serviceFeedbackService;

    @PostMapping("employee/{employee_id}")
    public ResponseEntity<ServiceFeedbackDetailsDTO> create(
            @PathVariable("employee_id") Long employeeId,
            @RequestBody CreateServiceFeedbackDTO serviceFeedbackDTO,
            UriComponentsBuilder uri
    ){
        ServiceFeedBack serviceFeedBack = serviceFeedbackService.create(employeeId, serviceFeedbackDTO);
        var url = uri.path("employee/{employee_id}").buildAndExpand(serviceFeedBack.getId()).toUri();

        return ResponseEntity.created(url).body(new ServiceFeedbackDetailsDTO(serviceFeedBack));
    }

    @GetMapping
    public ResponseEntity<List<ServiceFeedbackDetailsDTO>> findAll(){
        var serviceFeedbackList = serviceFeedbackService.getAll();

        return ResponseEntity.ok(serviceFeedbackList);
    }


    @GetMapping("{service_feedback_id}")
    public ResponseEntity<ServiceFeedbackDetailsDTO> findOne(
            @PathVariable("service_feedback_id") Long serviceFeedbackId
            ){
        var serviceFeedback = serviceFeedbackService.getOne(serviceFeedbackId);

        return ResponseEntity.ok(serviceFeedback);
    }

    @PostMapping("{service_feedback_id}/employee/{employee_id}")
    public ResponseEntity<ServiceFeedbackDetailsDTO> update(
            @PathVariable("service_feedback_id") Long serviceFeedbackId,
            @PathVariable("employee_id") Long employeeId,
            @RequestBody UpdateServiceFeedbackDTO serviceFeedbackDTO
            ) {
        var serviceFeedback = serviceFeedbackService.update(serviceFeedbackId, employeeId, serviceFeedbackDTO);

        return ResponseEntity.ok(serviceFeedback);
    }


    @DeleteMapping("{service_feedback_id}")
    public ResponseEntity<Void> delete(
            @PathVariable("service_feedback_id") Long serviceFeedbackId
    ){
        serviceFeedbackService.delete(serviceFeedbackId);
        return ResponseEntity.noContent().build();
    }

}
