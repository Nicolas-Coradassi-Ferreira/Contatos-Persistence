package br.com.nicoservices.contatospersistence.infra.exception;


import br.com.nicoservices.contatospersistence.exception.ContatoNaoEncontradoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContatoNaoEncontradoException.class)
    public ModelAndView contatoNaoEncontrado(Exception e){
        return new ModelAndView("exception/exception")
                .addObject("exceptionMessage", e.getMessage());
    }
}
