package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.CreateEnterpriseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.EnterpriseDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.UpdateEnterpriseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.EnterpriseService;
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
@RequestMapping("/enterprises")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping
    @Operation(summary = "Create a new enterprise", description = "Register a new enterprise in the database")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Enterprise created successfully", content =
            @Content(schema = @Schema(implementation = EnterpriseDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid data", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    public ResponseEntity<EnterpriseDetailsDTO> create(@RequestBody @Valid CreateEnterpriseDTO enterpriseDTO, UriComponentsBuilder uri) {
        var enterprise = enterpriseService.create(enterpriseDTO);
        var url = uri.path("enterprises/{enterprise_id}").buildAndExpand(enterprise.getId())
                .toUri();
        return ResponseEntity.created(url).body(new EnterpriseDetailsDTO(enterprise));
    }

    @GetMapping
    @Operation(summary = "Fetch all enterprises", description = "Retrieve a list of all enterprises from the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enterprises fetched successfully", content =
            @Content(schema = @Schema(implementation = EnterpriseDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    public ResponseEntity<List<EnterpriseDetailsDTO>> findAll(Pageable page){
        var enterpriseList = enterpriseService.getAll(page);

        return ResponseEntity.ok(enterpriseList);
    }

    @GetMapping("{enterprise_id}")
    @Operation(summary = "Fetch enterprise by ID", description = "Retrieve a specific enterprise from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enterprise fetched successfully", content =
            @Content(schema = @Schema(implementation = EnterpriseDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Enterprise not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "enterprise_id", description = "ID of the enterprise to be fetched", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<EnterpriseDetailsDTO> findOne(@PathVariable("enterprise_id") Long enterpriseId) {
        var enterprise = enterpriseService.getOne(enterpriseId);

        return ResponseEntity.ok(enterprise);
    }

    @PutMapping("{enterprise_id}")
    @Operation(summary = "Update enterprise by ID", description = "Update the details of an existing enterprise by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Enterprise updated successfully", content =
            @Content(schema = @Schema(implementation = EnterpriseDetailsDTO.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Enterprise not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid data", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "enterprise_id", description = "ID of the enterprise to be updated", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<EnterpriseDetailsDTO> update(
            @PathVariable("enterprise_id") Long enterpriseId,
            @RequestBody @Valid UpdateEnterpriseDTO enterpriseDTO
            ) {
        var enterprise = enterpriseService.update(enterpriseId, enterpriseDTO);

        return ResponseEntity.ok(enterprise);
    }

    @DeleteMapping("{enterprise_id}")
    @Operation(summary = "Delete enterprise by ID", description = "Remove an enterprise from the database by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Enterprise deleted successfully", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Enterprise not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "enterprise_id", description = "ID of the enterprise to be deleted", required = true, in = ParameterIn.PATH)
    })
    public ResponseEntity<EnterpriseDetailsDTO> update(@PathVariable("enterprise_id") Long enterpriseId) {
        enterpriseService.delete(enterpriseId);
        return ResponseEntity.noContent().build();
    }

}
