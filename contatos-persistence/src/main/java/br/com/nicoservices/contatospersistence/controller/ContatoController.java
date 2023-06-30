package br.com.nicoservices.contatospersistence.controller;


import br.com.nicoservices.contatospersistence.dto.EditarContatoRequest;
import br.com.nicoservices.contatospersistence.dto.NovoContatoRequest;
import br.com.nicoservices.contatospersistence.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;


    @GetMapping
    public ModelAndView index() {
        var contatos = service.buscarTodos();
        return new ModelAndView("index")
                .addObject("contatos", contatos);
    }

    @GetMapping("/novo")
    public String novo(NovoContatoRequest novoContatoRequest){
        return "formNovoContato";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid NovoContatoRequest novoContatoRequest, BindingResult validationResult){
        if (validationResult.hasErrors()){
            return "formNovoContato";
        }
        service.cadastrar(novoContatoRequest);
        return "redirect:/contatos";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id){
        var contato = service.buscarPorId(id);
        return new ModelAndView("formEditarContato")
                .addObject("editarContatoRequest", contato.toEditarContatoRequest());
    }

    @PostMapping("/atualizar")
    public String atualizar(@Valid EditarContatoRequest editarContatoRequest, BindingResult validationResult){
        if (validationResult.hasErrors()){
            return "formEditarContato";
        }
        var contato = service.buscarPorId(editarContatoRequest.id());
        contato.atualizarDados(editarContatoRequest);
        return "redirect:/contatos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){
        service.excluirPorId(id);
        return "redirect:/contatos";
    }

}
