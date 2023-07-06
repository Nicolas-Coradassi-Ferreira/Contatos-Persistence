package br.com.nicoservices.contatospersistence;

import br.com.nicoservices.contatospersistence.dto.usuario.NovoUsuarioForm;
import br.com.nicoservices.contatospersistence.exception.UsuarioJaCadastradoException;
import br.com.nicoservices.contatospersistence.model.usuario.Usuario;
import br.com.nicoservices.contatospersistence.repository.UsuarioRepository;
import br.com.nicoservices.contatospersistence.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class UsuarioServiceTest {

    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;
    @InjectMocks
    private UsuarioService usuarioService;


    @BeforeEach
    void beforeEach() {
        openMocks(this);
    }


    @Test
    void deveriaLancarExcecaoAoTentarCarregarUmUsuarioPorUsernameInexistente() {
        when(usuarioRepository.findByUsername("usernameInexistente"))
                .thenReturn(Optional.empty());

        var exception = assertThrows(UsernameNotFoundException.class, () -> {
            usuarioService.loadUserByUsername("usernameInexistente");
        });

        assertEquals("Usuário usernameInexistente não encontrado!", exception.getMessage());
        verify(usuarioRepository, times(1))
                .findByUsername("usernameInexistente");

    }

    @Test
    void deveriaLancarExcecaoAoTentarCadastrarUmUsuarioComUsernameJaCadastrado() {
        when(usuarioRepository.findByUsername("usernameExistente"))
                .thenReturn(Optional.of(new Usuario()));

        try {
            var dadosNovoUsuario = new NovoUsuarioForm("Nicolas Coradassi Ferreira", "usernameExistente", "nico1234");
            usuarioService.cadastrar(dadosNovoUsuario);
            fail();
        } catch (UsuarioJaCadastradoException e) {
            assertEquals("Usuario 'usernameExistente' já cadastrado!", e.getMessage());
        }
    }

    @Test
    void deveriaCadastrarUmNovoUsuarioNoBancoDeDados() {

        when(usuarioRepository.findByUsername("usernameInexistente"))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(any()))
                .thenReturn("nico1234");

        var dadosNovoUsuario = new NovoUsuarioForm("Nicolas Coradassi Ferreira", "usernameInexistente", "nico1234");

        usuarioService.cadastrar(dadosNovoUsuario);

        verify(usuarioRepository, times(1))
                .save(usuarioCaptor.capture());

        var usuarioCadastrado = usuarioCaptor.getValue();

        assertEquals("Nicolas Coradassi Ferreira", usuarioCadastrado.getNomeCompleto());
        assertEquals("usernameInexistente", usuarioCadastrado.getUsername());
        assertEquals("nico1234", usuarioCadastrado.getPassword());
    }

    @Test
    void deveriaAtualizarUmUsuarioNoBancoDeDados() {
        usuarioService.atualizar(new Usuario());

        verify(usuarioRepository, times(1)).saveAndFlush(any());
    }
}
