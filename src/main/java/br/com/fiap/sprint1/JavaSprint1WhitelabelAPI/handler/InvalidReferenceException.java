package br.com.fiap.sprint1.JavaSprint1WhitelabelAPI.handler;

public class InvalidReferenceException extends RuntimeException{
    public InvalidReferenceException (){
        super("Invalid Reference");
    }
}
