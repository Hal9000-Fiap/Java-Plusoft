package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.CreateEnterpriseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.EnterpriseDetailsDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.enterprise.UpdateEnterpriseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Enterprise;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.EnterpriseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Transactional
    public Enterprise create(CreateEnterpriseDTO enterpriseDTO) {
        Enterprise enterprise = new Enterprise(enterpriseDTO);

        return enterpriseRepository.save(enterprise);
    }

    public List<EnterpriseDetailsDTO> getAll(Pageable pageable) {
        return enterpriseRepository.findAll(pageable)
                .stream().map(EnterpriseDetailsDTO::new).toList();
    }

    public EnterpriseDetailsDTO getOne(Long enterpriseId) {
        Enterprise enterprise = enterpriseRepository.getReferenceById(enterpriseId);

        return new EnterpriseDetailsDTO(enterprise);
    }

    @Transactional
    public EnterpriseDetailsDTO update(Long enterpriseId, UpdateEnterpriseDTO enterpriseDTO) {
        Enterprise enterprise = enterpriseRepository.getReferenceById(enterpriseId);

        if(enterprise.getName() != null)
            enterprise.setName(enterpriseDTO.name());

        if(enterprise.getSegmentType() != null)
            enterprise.setSegmentType(enterpriseDTO.segmentType());

        enterpriseRepository.save(enterprise);

        return new EnterpriseDetailsDTO(enterprise);
    }

    @Transactional
    public void delete(Long enterpriseId) {
        enterpriseRepository.deleteById(enterpriseId);
    }

}
