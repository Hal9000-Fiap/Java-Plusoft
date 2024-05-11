package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.CreateResponseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.ResponseDetaisDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.UpdateResponseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.ResponseService;
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
    public ResponseEntity<List<ResponseDetaisDTO>> findAll(){
        var responseList = responseService.getAll();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{response_id}")
    public ResponseEntity<ResponseDetaisDTO> getOne(@PathVariable("response_id") Long responseId){
        var response = responseService.getOne(responseId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{response_id}")
    public ResponseEntity<ResponseDetaisDTO> update(
        @PathVariable("response_id") Long responseId,
        @RequestBody @Valid UpdateResponseDTO responseDTO
    ){
        var response = responseService.update(responseId, responseDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{response_id}")
    public ResponseEntity<Void> delete(@PathVariable("response_id") Long responseId){
        responseService.delete(responseId);

        return ResponseEntity.noContent().build();
    }

}
