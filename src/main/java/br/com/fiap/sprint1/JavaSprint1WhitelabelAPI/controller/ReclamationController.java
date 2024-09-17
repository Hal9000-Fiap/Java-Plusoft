package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.CreateReclamationDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.ReclamationDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Reclamation;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.ReclamationService;
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
@RequestMapping("/reclamations")
public class ReclamationController {

    @Autowired
    ReclamationService reclamationService;

    @PostMapping("/customer/{customer_id}/enterprise/{enterprise_id}")
    @Operation(summary = "Create a new reclamation", description = "Register a new reclamation for a customer and an enterprise")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reclamation created successfully", content =
            @Content(schema = @Schema(implementation = ReclamationDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid data", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "customer_id", description = "ID of the customer creating the reclamation", required = true, in = ParameterIn.PATH),
            @Parameter(name = "enterprise_id", description = "ID of the enterprise associated with the reclamation", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<ReclamationDetailsDTO> create(
            @PathVariable("customer_id") Long customerId,
            @PathVariable("enterprise_id") Long enterpriseId,
            @RequestBody @Valid CreateReclamationDTO reclamationDTO,
            UriComponentsBuilder uri
            ){
        Reclamation reclamation = reclamationService.create(customerId, enterpriseId, reclamationDTO);

        var url = uri.path("reclamations/{reclamation_id}/customer/{customer_id}/enterprise/{enterprise_id}")
                .buildAndExpand(reclamation.getId(), customerId, enterpriseId).toUri();

        return ResponseEntity.created(url).body(new ReclamationDetailsDTO(reclamation));
    }

    @GetMapping
    @Operation(summary = "Fetch all reclamations", description = "Retrieve a list of all reclamations from the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reclamations fetched successfully", content =
            @Content(schema = @Schema(implementation = ReclamationDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    public ResponseEntity<List<ReclamationDetailsDTO>> findAll(Pageable page) {
        var reclamationList = reclamationService.getAll(page);

        return ResponseEntity.ok(reclamationList);
    }

    @GetMapping("{reclamation_id}")
    @Operation(summary = "Fetch reclamation by ID", description = "Retrieve a specific reclamation from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reclamation fetched successfully", content =
            @Content(schema = @Schema(implementation = ReclamationDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Reclamation not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "reclamation_id", description = "ID of the reclamation to be fetched", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<ReclamationDetailsDTO> findOne(
            @PathVariable("reclamation_id") Long reclamationId
            ){
        var reclamation = reclamationService.getOne(reclamationId);

        return ResponseEntity.ok(reclamation);
    }

    @DeleteMapping("{reclamation_id}")
    @Operation(summary = "Delete reclamation by ID", description = "Remove a reclamation from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reclamation deleted successfully", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Reclamation not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "reclamation_id", description = "ID of the reclamation to be deleted", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<Void> delete(
            @PathVariable("reclamation_id") Long reclamationId
    ){
        reclamationService.delete(reclamationId);
        return ResponseEntity.noContent().build();
    }

}
