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

    @Autowired
    private ContatoRepository repository;


    public List<Contato> buscarTodos() {
        var contatos = repository.findAll();
        Collections.sort(contatos);
        return contatos;
    }

    public void salvar(DadosNovoContato dadosNovoContato) {
        repository.save(new Contato(dadosNovoContato));
    }

    public void salvar(DadosEditarContato dadosContatoAtualizado) {
        if (repository.existsById(dadosContatoAtualizado.id())) {
            var contato = new Contato(dadosContatoAtualizado);
            repository.save(contato);
            return;
        }
        throw new EntityNotFoundException();
    }

    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }

    public Contato buscarPorId(Long id) {
        var contato = repository.findById(id);
        if (contato.isPresent()) {
            return contato.get();
        }
        throw new EntityNotFoundException();
    }
}
