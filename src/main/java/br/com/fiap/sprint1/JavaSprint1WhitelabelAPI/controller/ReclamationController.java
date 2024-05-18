package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.AddEmployeeInReclamationDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.CreateReclamationDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.reclamation.ReclamationDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Reclamation;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.ReclamationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/reclamations")
public class ReclamationController {

    @Autowired
    ReclamationService reclamationService;

    @PostMapping("/customer/{customer_id}/enterprise/{eterprise_id}")
    public ResponseEntity<ReclamationDetailsDTO> create(@PathVariable("customer_id") Long customerId,
                                                        @PathVariable("eterprise_id") Long enterpriseId,
                                                        @RequestBody @Valid CreateReclamationDTO reclamationDTO,
                                                        UriComponentsBuilder uri){
        Reclamation reclamation = reclamationService.create(customerId, enterpriseId, reclamationDTO);
        var url = uri.path("reclamations/{reclamation_id}/customer/{customer_id}/enterprise/{eterprise_id}")
                .buildAndExpand(reclamation.getId(), customerId, enterpriseId).toUri();

        return ResponseEntity.created(url).body(new ReclamationDetailsDTO(reclamation));
    }

    @GetMapping
    public ResponseEntity<List<ReclamationDetailsDTO>> findAll(Pageable pageable) {
        var reclamationList = reclamationService.getAll(pageable);
        return ResponseEntity.ok(reclamationList);
    }

    @GetMapping("/{reclamation_id}")
    public ResponseEntity<ReclamationDetailsDTO> findOne(@PathVariable("reclamation_id") Long reclamationId){
        var reclamation = reclamationService.getOne(reclamationId);
        return ResponseEntity.ok(reclamation);
    }

    @PutMapping("add-employee/{reclamation_id}")
    public ResponseEntity<ReclamationDetailsDTO> addEmployeesInReclamation(@PathVariable("reclamation_id") Long reclamationId,
                                                                           @RequestBody @Valid AddEmployeeInReclamationDTO reclamationDTO){
        var reclamation = reclamationService.addEmployeesInReclamation(reclamationId, reclamationDTO);
        return ResponseEntity.ok(reclamation);
    }

    @DeleteMapping("/{reclamation_id}")
    public ResponseEntity<Void> delete(@PathVariable("reclamation_id") Long reclamationId){
        reclamationService.delete(reclamationId);
        return ResponseEntity.noContent().build();
    }

}
