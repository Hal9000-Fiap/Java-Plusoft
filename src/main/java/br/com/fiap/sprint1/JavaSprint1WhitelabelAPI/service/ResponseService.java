package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.CreateResponseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.ResponseDetaisDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.UpdateResponseDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Reclamation;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Response;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.ReclamationRepositry;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.ResponseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    ResponseRepository responseRepository;
    @Autowired
    ReclamationRepositry reclamationRepository;

    @Transactional
    public Response create(Long reclamationId, CreateResponseDTO responseDTO) {
        Reclamation reclamation = reclamationRepository.getReferenceById(reclamationId);
        Response response = new Response(responseDTO);

        response.setReclamation(reclamation);

        return responseRepository.save(response);
    }

    public List<ResponseDetaisDTO> getAll() {
        List<ResponseDetaisDTO> responseList = responseRepository.findAll()
                .stream().map(ResponseDetaisDTO::new).toList();

        return responseList;
    }

    public ResponseDetaisDTO getOne(Long responseId) {
       Response response = responseRepository.getReferenceById(responseId);

       return new ResponseDetaisDTO(response);
    }

    @Transactional
    public ResponseDetaisDTO update(Long responseId, UpdateResponseDTO responseDTO) {
        Response response = responseRepository.getReferenceById(responseId);

        if(!response.getMessage().isEmpty())
            response.setMessage(responseDTO.message());

        return new ResponseDetaisDTO(response);
    }

    @Transactional
    public void delete(Long responseId) {
        responseRepository.deleteById(responseId);
    }

}
