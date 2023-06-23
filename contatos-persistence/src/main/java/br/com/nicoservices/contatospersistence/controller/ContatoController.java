package br.com.nicoservices.contatospersistence.controller;

import br.com.nicoservices.contatospersistence.dto.DadosNovoContato;
import br.com.nicoservices.contatospersistence.dto.DadosEditarContato;
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

    private final ContatoService service;

    @Autowired
    public ContatoController(ContatoService service){
        this.service = service;
    }

    @GetMapping
    public ModelAndView index() {
        var contatos = service.buscarTodos();
        return new ModelAndView("index").addObject("contatos", contatos);
    }

    @GetMapping("/novo")
    public String novo(DadosNovoContato dadosNovoContato) {
        return "formNovoContato";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public String cadastrar(@Valid DadosNovoContato dadosContato, BindingResult validationResult) {
        if (validationResult.hasErrors()) return "formNovoContato";
        service.salvar(dadosContato);
        return "redirect:/contatos";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        var contato = service.buscarReferenciaPorId(id);
        return new ModelAndView("formEditarContato").addObject(contato.toDadosEditarContato());
    }

    @PostMapping("/atualizar")
    @Transactional
    public String atualizar(@Valid DadosEditarContato dadosAtualizados, BindingResult validationResult) {
        if (validationResult.hasErrors()) return "formEditarContato";
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
