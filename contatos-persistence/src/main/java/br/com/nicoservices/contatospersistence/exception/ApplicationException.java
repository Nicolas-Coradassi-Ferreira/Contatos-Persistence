package br.com.nicoservices.contatospersistence.exception;

public class ApplicationException extends RuntimeException{

    public ApplicationException(){}

    public ApplicationException(String msg){
        super(msg);
    }
}
