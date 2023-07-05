package br.com.nicoservices.contatospersistence.service;

import br.com.nicoservices.contatospersistence.dto.usuario.NovoUsuarioForm;
import br.com.nicoservices.contatospersistence.exception.UsuarioJaCadastradoException;
import br.com.nicoservices.contatospersistence.model.usuario.Usuario;
import br.com.nicoservices.contatospersistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Usuário %s não encontrado!", username)
                ));
    }

    @Transactional
    public void cadastrar(NovoUsuarioForm novoUsuarioForm) {
        var usuario = usuarioRepository.findByUsername(novoUsuarioForm.username());
        if (usuario.isPresent()) {
            throw new UsuarioJaCadastradoException(String.format("Usuario '%s' já cadastrado!", novoUsuarioForm.username()));
        }
        usuarioRepository.save(new Usuario(
                novoUsuarioForm.nomeCompleto(),
                novoUsuarioForm.username(),
                passwordEncoder.encode(novoUsuarioForm.password())
        ));
    }

    @Transactional
    public void atualizar(Usuario u){
        usuarioRepository.saveAndFlush(u);
    }
}
