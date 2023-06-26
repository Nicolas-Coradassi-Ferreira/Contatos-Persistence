package br.com.nicoservices.contatospersistence.service;

import br.com.nicoservices.contatospersistence.dto.DadosEditarContato;
import br.com.nicoservices.contatospersistence.dto.DadosNovoContato;
import br.com.nicoservices.contatospersistence.repository.ContatoRepository;
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

    public void salvar(DadosEditarContato dadosAtualizados) {
        var contato = buscarPorId(dadosAtualizados.id());
        contato.atualizarDados(dadosAtualizados);
    }

    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }

    public Contato buscarPorId(Long id) {
        return repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
