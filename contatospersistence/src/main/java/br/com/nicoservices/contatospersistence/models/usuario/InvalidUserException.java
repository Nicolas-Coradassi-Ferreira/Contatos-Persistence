package br.com.nicoservices.contatospersistence.models.usuario;

public class InvalidUserException extends RuntimeException{

    public InvalidUserException(){
        super();
    }

    public InvalidUserException(String msg){
        super(msg);
    }
}
