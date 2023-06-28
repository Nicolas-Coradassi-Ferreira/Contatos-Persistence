package br.com.nicoservices.contatospersistence.controller;

import br.com.nicoservices.contatospersistence.dto.NovoContatoRequest;
import br.com.nicoservices.contatospersistence.dto.EditarContatoRequest;
import br.com.nicoservices.contatospersistence.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String novo(NovoContatoRequest novoContatoRequest) {
        return "contato/formNovoContato";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public String cadastrar(@Valid NovoContatoRequest dadosContato, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "contato/formNovoContato";
        }
        service.salvar(dadosContato);
        return "redirect:/contatos";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        var contato = service.buscarPorId(id);
        return new ModelAndView("contato/formEditarContato")
                .addObject(contato.toDadosEditarContato());
    }

    @PostMapping("/atualizar")
    @Transactional
    public String atualizar(@Valid EditarContatoRequest dadosAtualizados, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "contato/formEditarContato";
        }
        service.salvar(dadosAtualizados);
        return "redirect:/contatos";
    }

    @PostMapping("/excluir/{id}")
    @Transactional
    public String excluir(@PathVariable Long id) {
        service.excluirPorId(id);
        return "redirect:/contatos";
    }


}
