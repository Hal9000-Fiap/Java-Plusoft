package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.CreateResponseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.ResponseDetaisDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.UpdateResponseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    ResponseService responseService;

    @PostMapping("/reclamation/{reclamation_id}")
    @Operation(summary = "Create a response", description = "Create a new response for a reclamation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Response created successfully", content =
            @Content(schema = @Schema(implementation = ResponseDetaisDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "reclamation_id", description = "ID of the reclamation to which the response belongs", required = true),
            @Parameter(name = "responseDTO", description = "Details of the response being created", required = true)
    })
    public ResponseEntity<ResponseDetaisDTO> create(
        @PathVariable("reclamation_id") Long reclamationId,
        @RequestBody @Valid CreateResponseDTO responseDTO,
        UriComponentsBuilder uri
    ){
        var response = responseService.create(reclamationId, responseDTO);
        var url = uri.path("reclamation/{reclamation_id}")
                .buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(url).body(new ResponseDetaisDTO(response));
    }

    @GetMapping
    @Operation(summary = "Get all responses", description = "Retrieve all responses from the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Responses retrieved successfully", content =
            @Content(schema = @Schema(implementation = ResponseDetaisDTO.class))),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    public ResponseEntity<List<ResponseDetaisDTO>> findAll(){
        var responseList = responseService.getAll();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("{response_id}")
    @Operation(summary = "Get a response by ID", description = "Retrieve a specific response by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Response retrieved successfully", content =
            @Content(schema = @Schema(implementation = ResponseDetaisDTO.class))),
            @ApiResponse(responseCode = "404", description = "Response not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "response_id", description = "ID of the response to be retrieved", required = true)
    })
    public ResponseEntity<ResponseDetaisDTO> getOne(@PathVariable("response_id") Long responseId){
        var response = responseService.getOne(responseId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{response_id}")

    @Operation(summary = "Update a response", description = "Update an existing response by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Response updated successfully", content =
            @Content(schema = @Schema(implementation = ResponseDetaisDTO.class))),
            @ApiResponse(responseCode = "404", description = "Response not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "response_id", description = "ID of the response to be updated", required = true),
            @Parameter(name = "responseDTO", description = "Details of the response to be updated", required = true)
    })
    public ResponseEntity<ResponseDetaisDTO> update(
        @PathVariable("response_id") Long responseId,
        @RequestBody @Valid UpdateResponseDTO responseDTO
    ){
        var response = responseService.update(responseId, responseDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{response_id}")
    @Operation(summary = "Delete a response", description = "Delete a response by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Response deleted successfully", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Response not found", content =
            @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403", description = "Not authorized or invalid token", content =
            @Content(schema = @Schema()))
    })
    @Parameters({
            @Parameter(name = "response_id", description = "ID of the response to be deleted", required = true)
    })
    public ResponseEntity<Void> delete(@PathVariable("response_id") Long responseId){
        responseService.delete(responseId);

        return ResponseEntity.noContent().build();
    }

}
