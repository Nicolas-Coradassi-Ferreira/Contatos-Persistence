package br.com.nicoservices.contatospersistence.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView contatoNaoEncontrado(){
        return new ModelAndView("exceptions/exception")
                .addObject("exceptionMessage", "Não foi possível encontrar o contato!");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView erroDesconhecido(){
        return new ModelAndView("exceptions/exception")
                .addObject("exceptionMessage", "Erro desconhecido, tente se conectar à página novamente!");
    }
}
