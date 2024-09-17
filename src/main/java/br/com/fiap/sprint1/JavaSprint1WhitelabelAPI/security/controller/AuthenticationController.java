package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security.controller;

import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.model.Customer;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security.dto.AuthenticationDataDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security.dto.TokenJwtDTO;
import br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJwtDTO> login(@RequestBody AuthenticationDataDTO data){
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(token);
        var tokenJwt = tokenService.generateToken((Customer) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDTO(tokenJwt));
    }
}
