package br.com.nicoservices.contatospersistence.service;

import br.com.nicoservices.contatospersistence.dto.DadosEditarContato;
import br.com.nicoservices.contatospersistence.dto.DadosNovoContato;
import br.com.nicoservices.contatospersistence.jpa.ContatoRepository;
import br.com.nicoservices.contatospersistence.model.Contato;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository repository;


    @Autowired
    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }


    public List<Contato> buscarTodos() {
        var contatos = repository.findAll();
        Collections.sort(contatos);
        return contatos;
    }

    public void salvar(DadosNovoContato dadosNovoContato) {
        repository.save(new Contato(dadosNovoContato));
    }

    public void salvar(DadosEditarContato dadosContatoAtualizado) {
        if (contatoExistePorId(dadosContatoAtualizado.id()))
            repository.save(new Contato(dadosContatoAtualizado));
        else throw new EntityNotFoundException();
    }

    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }

    public Contato buscarReferenciaPorId(Long id) {
        if (contatoExistePorId(id))
            return repository.getReferenceById(id);
        else throw new EntityNotFoundException();
    }

    private Boolean contatoExistePorId(Long id) {
        var contato = repository.findById(id);
        if (contato.isPresent()) return Boolean.TRUE;
        else return Boolean.FALSE;
    }
}
