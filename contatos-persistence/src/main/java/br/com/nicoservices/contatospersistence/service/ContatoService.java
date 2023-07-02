package br.com.nicoservices.contatospersistence.service;

import br.com.nicoservices.contatospersistence.dto.EditarContatoForm;
import br.com.nicoservices.contatospersistence.dto.NovoContatoForm;
import br.com.nicoservices.contatospersistence.exception.ApplicationException;
import br.com.nicoservices.contatospersistence.model.Contato;
import br.com.nicoservices.contatospersistence.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.sort;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public List<Contato> buscarTodos(){
        var contatos = repository.findAll();
        sort(contatos);
        return contatos;
    }

    public void cadastrar(NovoContatoForm novoContatoRequest) {
        repository.save(new Contato(novoContatoRequest));
    }

    public void atualizar(EditarContatoForm editarContatoForm){
        var contato = buscarPorId(editarContatoForm.id());
        contato.atualizarDados(editarContatoForm);
        repository.save(contato);
    }

    public Contato buscarPorId(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ApplicationException("Não foi possível encontrar o contato específicado!"));
    }

    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }
}
