package br.com.nicoservices.contatospersistence.infra.exception;

import br.com.nicoservices.contatospersistence.exception.ApplicationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationException(ApplicationException exception){
        return new ModelAndView("exceptions/exception")
                .addObject("exceptionMessage", exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView erroDesconhecido(Exception exception){
        return new ModelAndView("exceptions/exception")
                .addObject("exceptionMessage", "Erro desconhecido: " + exception.getMessage());
    }
}
