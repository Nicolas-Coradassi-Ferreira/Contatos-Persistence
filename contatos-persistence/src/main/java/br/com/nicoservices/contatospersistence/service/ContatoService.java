package br.com.nicoservices.contatospersistence.service;

import br.com.nicoservices.contatospersistence.dto.contato.EditarContatoForm;
import br.com.nicoservices.contatospersistence.dto.contato.NovoContatoForm;
import br.com.nicoservices.contatospersistence.model.contato.Contato;
import br.com.nicoservices.contatospersistence.model.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

@Service
public class ContatoService {

    @Autowired
    private UsuarioService usuarioService;


    public List<Contato> buscarTodos(String username){
        var usuario = (Usuario) usuarioService.loadUserByUsername(username);
        var contatos = new ArrayList<>(usuario.getContatos());
        sort(contatos);
        return contatos;
    }

    public void novo(String username, NovoContatoForm novoContatoForm) {
        var usuario = (Usuario) usuarioService.loadUserByUsername(username);
        usuario.adicionarContato(new Contato(novoContatoForm));
        usuarioService.atualizar(usuario);
    }

    public void atualizar(String username, EditarContatoForm editarContatoForm){
        var usuario = (Usuario) usuarioService.loadUserByUsername(username);
        var contato = usuario.getContatoPorId(editarContatoForm.id());
        contato.atualizarDados(editarContatoForm);
        usuarioService.atualizar(usuario);
    }

    public Contato buscarPorId(String username, Long contatoId) {
        var usuario = (Usuario) usuarioService.loadUserByUsername(username);
        return usuario.getContatoPorId(contatoId);
    }

    public void excluirPorId(String username, Long id) {
        var usuario = (Usuario) usuarioService.loadUserByUsername(username);
        usuario.removerContatoPorId(id);
        usuarioService.atualizar(usuario);
    }
}
