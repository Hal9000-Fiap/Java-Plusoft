package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.CreateEnterpriseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.EnterpriseDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.UpdateEnterpriseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service.EnterpriseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping
    public ResponseEntity<EnterpriseDetailsDTO> create(@RequestBody @Valid CreateEnterpriseDTO enterpriseDTO,
                                                       UriComponentsBuilder uri) {
        var enterprise = enterpriseService.create(enterpriseDTO);
        var url = uri.path("enterprise/{enterprise_id}").buildAndExpand(enterprise.getId()).toUri();
        return ResponseEntity.created(url).body(new EnterpriseDetailsDTO(enterprise));
    }

    @GetMapping
    public ResponseEntity<List<EnterpriseDetailsDTO>> findAll(Pageable pageable){
        var enterpriseList = enterpriseService.getAll(pageable);
        return ResponseEntity.ok(enterpriseList);
    }

    @GetMapping("/{enterprise_id}")
    public ResponseEntity<EnterpriseDetailsDTO> findOne(@PathVariable("enterprise_id") Long enterpriseId) {
        var enterprise = enterpriseService.getOne(enterpriseId);
        return ResponseEntity.ok(enterprise);
    }

    @PutMapping("/{enterprise_id}")
    public ResponseEntity<EnterpriseDetailsDTO> update(@PathVariable("enterprise_id") Long enterpriseId,
                                                       @RequestBody @Valid UpdateEnterpriseDTO enterpriseDTO) {
        var enterprise = enterpriseService.update(enterpriseId, enterpriseDTO);
        return ResponseEntity.ok(enterprise);
    }

    @DeleteMapping("/{enterprise_id}")
    public ResponseEntity<EnterpriseDetailsDTO> update(@PathVariable("enterprise_id") Long enterpriseId) {
        enterpriseService.delete(enterpriseId);
        return ResponseEntity.noContent().build();
    }

}
