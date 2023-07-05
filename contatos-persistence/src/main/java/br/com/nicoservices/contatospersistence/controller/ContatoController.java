package br.com.nicoservices.contatospersistence.controller;


import br.com.nicoservices.contatospersistence.dto.contato.EditarContatoForm;
import br.com.nicoservices.contatospersistence.dto.contato.NovoContatoForm;
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

import java.security.Principal;


@Controller
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;


    @GetMapping("/{evento}")
    public ModelAndView index(@PathVariable String evento, Principal usuario) {
        var contatos = contatoService.buscarTodos(usuario.getName());
        return new ModelAndView("index")
                .addObject("contatos", contatos)
                .addObject("evento", evento);
    }

    @GetMapping("/novo")
    public String novo(NovoContatoForm novoContatoForm){
        return "contato/formNovoContato";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid NovoContatoForm novoContatoForm,
                            BindingResult validationResult,
                            Principal usuario){
        if (validationResult.hasErrors()){
            return "contato/formNovoContato";
        }
        contatoService.novo(usuario.getName(), novoContatoForm);
        return "redirect:/contatos/cadastrado";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id){
        var contato = contatoService.buscarPorId(id);
        return new ModelAndView("contato/formEditarContato")
                .addObject("editarContatoForm", contato.toEditarContatoForm());
    }

    @PostMapping("/atualizar")
    public String atualizar(@Valid EditarContatoForm editarContatoForm, BindingResult validationResult){
        if (validationResult.hasErrors()){
            return "contato/formEditarContato";
        }
        contatoService.atualizar(editarContatoForm);
        return "redirect:/contatos/atualizado";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, Principal usuario){
        contatoService.excluirPorId(usuario.getName(), id);
        return "redirect:/contatos/excluido";
    }

}
