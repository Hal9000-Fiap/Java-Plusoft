package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.CreateServiceFeedbackDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.ServiceFeedbackDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.serviceFeedback.UpdateServiceFeedbackDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.ServiceFeedBack;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.ServiceFeedbackService;
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
@RequestMapping("/servicefeedbacks")
public class ServiceFeedbackController {

    @Autowired
    ServiceFeedbackService serviceFeedbackService;

    @PostMapping("employee/{employee_id}")
    @Operation(summary = "Create a new service feedback", description = "Create a feedback entry for a specific employee.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Feedback created successfully", content =
            @Content(schema = @Schema(implementation = ServiceFeedbackDetailsDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data provided"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request")
    })
    @Parameters({
            @Parameter(name = "employee_id", description = "ID of the employee receiving the feedback", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<ServiceFeedbackDetailsDTO> create(
            @PathVariable("employee_id") Long employeeId,
            @RequestBody @Valid CreateServiceFeedbackDTO serviceFeedbackDTO,
            UriComponentsBuilder uri
    ){
        ServiceFeedBack serviceFeedBack = serviceFeedbackService.create(employeeId, serviceFeedbackDTO);
        var url = uri.path("employee/{employee_id}").buildAndExpand(serviceFeedBack.getId()).toUri();

        return ResponseEntity.created(url).body(new ServiceFeedbackDetailsDTO(serviceFeedBack));
    }

    @GetMapping
    @Operation(summary = "Get all service feedbacks", description = "Retrieve a list of all service feedbacks.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback list retrieved successfully", content =
            @Content(schema = @Schema(implementation = ServiceFeedbackDetailsDTO.class))),
            @ApiResponse(responseCode = "403", description = "Unauthorized request")
    })
    public ResponseEntity<List<ServiceFeedbackDetailsDTO>> findAll(Pageable page){
        var serviceFeedbackList = serviceFeedbackService.getAll(page);

        return ResponseEntity.ok(serviceFeedbackList);
    }


    @GetMapping("{service_feedback_id}")
    @Operation(summary = "Get a specific service feedback", description = "Retrieve details of a specific service feedback by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback retrieved successfully", content =
            @Content(schema = @Schema(implementation = ServiceFeedbackDetailsDTO.class))),
            @ApiResponse(responseCode = "404", description = "Feedback not found"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request")
    })
    @Parameters({
            @Parameter(name = "service_feedback_id", description = "ID of the feedback to retrieve", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<ServiceFeedbackDetailsDTO> findOne(
            @PathVariable("service_feedback_id") Long serviceFeedbackId
            ){
        var serviceFeedback = serviceFeedbackService.getOne(serviceFeedbackId);

        return ResponseEntity.ok(serviceFeedback);
    }

    @PutMapping("{service_feedback_id}/employee/{employee_id}")
    @Operation(summary = "Update a service feedback", description = "Update an existing service feedback for a specific employee.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback updated successfully", content =
            @Content(schema = @Schema(implementation = ServiceFeedbackDetailsDTO.class))),
            @ApiResponse(responseCode = "404", description = "Feedback not found"),
            @ApiResponse(responseCode = "403", description = "Unauthorized request")
    })
    @Parameters({
            @Parameter(name = "service_feedback_id", description = "ID of the feedback to update", required = true, in = ParameterIn.PATH),
            @Parameter(name = "employee_id", description = "ID of the employee associated with the feedback", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<ServiceFeedbackDetailsDTO> update(
            @PathVariable("service_feedback_id") Long serviceFeedbackId,
            @PathVariable("employee_id") Long employeeId,
            @RequestBody @Valid UpdateServiceFeedbackDTO serviceFeedbackDTO
            ) {
        var serviceFeedback = serviceFeedbackService.update(serviceFeedbackId, employeeId, serviceFeedbackDTO);

        return ResponseEntity.ok(serviceFeedback);
    }


    @DeleteMapping("{service_feedback_id}")
    @Operation(summary = "Delete service feedback", description = "Delete service feedback")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Service Feedback deleted successfully", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Service Feedback not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "service_feedback_id", description = "ID of the feedback to retrieve", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(
            @PathVariable("service_feedback_id") Long serviceFeedbackId
    ){
        serviceFeedbackService.delete(serviceFeedbackId);
        return ResponseEntity.noContent().build();
    }

}
