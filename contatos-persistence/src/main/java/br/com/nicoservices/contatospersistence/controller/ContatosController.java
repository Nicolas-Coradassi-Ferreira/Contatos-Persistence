package br.com.nicoservices.contatospersistence.controller;

import br.com.nicoservices.contatospersistence.model.contato.Contato;
import br.com.nicoservices.contatospersistence.jpa.ContatoRepository;
import br.com.nicoservices.contatospersistence.model.contato.EditarContatoRequest;
import br.com.nicoservices.contatospersistence.model.contato.NovoContatoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/contatos")
public class ContatosController {

    @Autowired
    private ContatoRepository repository;

    @GetMapping
    public ModelAndView index(){
        List<Contato> contatos = repository.findAll();
        contatos.forEach(Contato::formatarTelefones);
        Collections.sort(contatos);
        return new ModelAndView("index").addObject("contatos", contatos);
    }

    @GetMapping("/novo")
    public String novo(NovoContatoRequest novoContatoRequest){
        return "formNovoContato";
    }

    @PostMapping("/novo")
    @Transactional
    public String cadastrar(@Valid NovoContatoRequest dadosContato, BindingResult validationResult){
        if (validationResult.hasErrors()) return "formNovoContato";
        repository.save(new Contato(dadosContato));
        return "redirect:/contatos";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id){
        Contato c = repository.getReferenceById(id);
        return new ModelAndView("formEditarContato").addObject(new EditarContatoRequest(c));
    }

    @PostMapping("/editar/{id}")
    @Transactional
    public String atualizar(@PathVariable Long id,
                            @Valid EditarContatoRequest dadosAtualizados,
                            BindingResult validationResult){

        if (validationResult.hasErrors()) return "formEditarContato";
        Contato contato = repository.findById(id).get();
        contato.atualizar(dadosAtualizados);
        repository.save(contato);
        return "redirect:/contatos";
    }

    @PostMapping("/excluir/{id}")
    @Transactional
    public String excluir(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/contatos";
    }


}
