package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.service;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.dto.response.ResponseDetaisDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Response;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.repository.ResponseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    ResponseRepository responseRepository;

    @Transactional
    public ResponseDetaisDTO create() {

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
    public void delete(Long responseId) {
        responseRepository.deleteById(responseId);
    }

}
