package br.com.nicoservices.contatospersistence.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request){

        ModelAndView mv = new ModelAndView("exceptions/exception");

        var status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            var codigoStatus = Integer.parseInt(status.toString());

            if (codigoStatus == HttpStatus.NOT_FOUND.value()){
                mv.addObject("exceptionMessage",
                        "Página não encontrada ou requisição não mapeada!");
                mv.addObject("errorCode", "Error code: " + codigoStatus);
                return mv;
            } else {
                mv.addObject("exceptionMessage",
                        "Erro não tratado!");
                mv.addObject("errorCode", "Error code: " + codigoStatus);
                return mv;
            }

        }

        return mv.addObject("exceptionMessage", "Erro não tratado!");
    }
}
