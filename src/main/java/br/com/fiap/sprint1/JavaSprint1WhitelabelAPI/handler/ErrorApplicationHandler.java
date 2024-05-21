package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorApplicationHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidReferenceException.class)
    public ResponseEntity error400(){
        return ResponseEntity.badRequest().build();
    }

}
