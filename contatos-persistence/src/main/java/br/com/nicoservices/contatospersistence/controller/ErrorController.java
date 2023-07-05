package br.com.nicoservices.contatospersistence.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public ModelAndView errorHandler(HttpServletRequest request){

        var errorCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        var errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (errorCode == 404 || errorCode == 400) {
            return new ModelAndView("exception/erro")
                    .addObject("errorCode", errorCode)
                    .addObject("errorMessage", "Página não encontrada ou rota não mapeada!");
        }
        return new ModelAndView("exception/erro")
                .addObject("errorCode", errorCode)
                .addObject("errorMessage", errorMessage);
    }
}
