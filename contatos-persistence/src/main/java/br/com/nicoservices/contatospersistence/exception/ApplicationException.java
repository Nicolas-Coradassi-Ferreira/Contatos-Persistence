package br.com.nicoservices.contatospersistence.exception;

public class ApplicationException extends RuntimeException{

    public ApplicationException(){
        super();
    }

    public ApplicationException(String msg){
        super(msg);
    }

}
