package br.com.nicoservices.contatospersistence.exception;

public class ContatoNaoEncontradoException extends RuntimeException{
    public ContatoNaoEncontradoException(String msg){
        super(msg);
    }
}
