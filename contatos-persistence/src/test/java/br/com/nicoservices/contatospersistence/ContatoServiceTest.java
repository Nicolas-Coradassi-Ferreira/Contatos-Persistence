package br.com.nicoservices.contatospersistence;

import br.com.nicoservices.contatospersistence.dto.contato.EditarContatoForm;
import br.com.nicoservices.contatospersistence.dto.contato.NovoContatoForm;
import br.com.nicoservices.contatospersistence.exception.ContatoNaoEncontradoException;
import br.com.nicoservices.contatospersistence.model.contato.Contato;
import br.com.nicoservices.contatospersistence.model.usuario.Usuario;
import br.com.nicoservices.contatospersistence.service.ContatoService;
import br.com.nicoservices.contatospersistence.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class ContatoServiceTest {

    @MockBean
    private UsuarioService usuarioService;
    @InjectMocks
    private ContatoService contatoService;
    private Usuario usuarioTeste;


    @BeforeEach
    void beforeEach(){
        openMocks(this);
        usuarioTeste = usuarioTeste();
        when(usuarioService.loadUserByUsername(any()))
                .thenReturn(usuarioTeste);
    }


    @Test
    void deveriaRetornarUmaListaDeContatosDeUmUsuarioOrdenadaPorNome(){

        var contatosRecebidos = contatoService.buscarTodos("nomeUsuario");
        var contatoRecebido1 = contatosRecebidos.get(0);
        var contatoRecebido2 = contatosRecebidos.get(1);
        var contatoRecebido3 = contatosRecebidos.get(2);

        verify(usuarioService, times(1)).loadUserByUsername("nomeUsuario");

        assertEquals(6L, contatoRecebido1.getId());
        assertEquals("Aline", contatoRecebido1.getNome());
        assertEquals("Silva Ferreira", contatoRecebido1.getSobrenome());
        assertEquals(LocalDate.of(2005, 2, 6), contatoRecebido1.getDataNascimento());
        assertEquals("98574-6534;91234-1432", contatoRecebido1.getTelefones());
        assertEquals("Prima", contatoRecebido1.getGrauParentesco());

        assertEquals(1L, contatoRecebido2.getId());
        assertEquals("Daniel", contatoRecebido2.getNome());
        assertEquals("Alves Pereira", contatoRecebido2.getSobrenome());
        assertEquals(LocalDate.of(1998, 4, 2), contatoRecebido2.getDataNascimento());
        assertEquals("98574-6876;91234-2542", contatoRecebido2.getTelefones());
        assertEquals("Irmao", contatoRecebido2.getGrauParentesco());

        assertEquals(3L, contatoRecebido3.getId());
        assertEquals("Nicolas", contatoRecebido3.getNome());
        assertEquals("C. Ferreira", contatoRecebido3.getSobrenome());
        assertEquals(LocalDate.of(2000, 7, 5), contatoRecebido3.getDataNascimento());
        assertEquals("98574-6323;91234-5678", contatoRecebido3.getTelefones());
        assertEquals("Amigo", contatoRecebido3.getGrauParentesco());
    }

    @Test
    void deveriaAdicionarUmNovoContatoNaListaDeContatosDoUsuario(){

        var dadosNovoContatoDeveSerAdicionadoNaLista = new NovoContatoForm(
                "Matheus",
                "Silveira",
                LocalDate.of(2000,2,2),
                "92354-6323;92344-5678",
                "Pai"
        );

        contatoService.novo("nomeUsuario", dadosNovoContatoDeveSerAdicionadoNaLista);

        var contatosUsuario = usuarioTeste.getContatos();
        var contatoAdicionado = contatosUsuario.get(contatosUsuario.size() - 1);

        verify(usuarioService, times(1)).atualizar(usuarioTeste);

        assertEquals("Matheus", contatoAdicionado.getNome());
        assertEquals("Silveira", contatoAdicionado.getSobrenome());
        assertEquals(LocalDate.of(2000,2,2), contatoAdicionado.getDataNascimento());
        assertEquals("92354-6323;92344-5678", contatoAdicionado.getTelefones());
        assertEquals("Pai", contatoAdicionado.getGrauParentesco());

    }

    @Test
    void deveriaAtualizarOsDadosDeUmContatoDoUsuario(){

        var dadosContatoAtualizados = new EditarContatoForm(
                1L,
                "Dãnyel",
                "Pereira Alves",
                LocalDate.of(1995, 5, 3),
                "98574-6876",
                "Brother");

        contatoService.atualizar("nomeUsuario", dadosContatoAtualizados);

        var contatoAtualizado = usuarioTeste.getContatos().get(2);

        verify(usuarioService, times(1)).atualizar(usuarioTeste);
        assertEquals(1L, contatoAtualizado.getId());
        assertEquals("Dãnyel", contatoAtualizado.getNome());
        assertEquals("Pereira Alves", contatoAtualizado.getSobrenome());
        assertEquals(LocalDate.of(1995, 5, 3), contatoAtualizado.getDataNascimento());
        assertEquals("98574-6876", contatoAtualizado.getTelefones());
        assertEquals("Brother", contatoAtualizado.getGrauParentesco());
    }

    @Test
    void deveriaLancarExcecaoAoTentarAtualizarOsDadosDeUmContatoDoUsuarioComIdInexistente(){
        var contatoComIdInvalido = new EditarContatoForm(
                70L, "", "", LocalDate.now(), "", "");

        assertThrows(ContatoNaoEncontradoException.class, () -> {
            contatoService.atualizar("nomeUsuario", contatoComIdInvalido);
        });
    }

    @Test
    void deveriaBuscarUmContatoPorIdNaListaDeContatosDoUsuario(){

        var contatoObtido = contatoService.buscarPorId("nomeUsuario", 3L);

        verify(usuarioService, times(1)).loadUserByUsername("nomeUsuario");
        assertEquals(3L, contatoObtido.getId());
        assertEquals("Nicolas", contatoObtido.getNome());
        assertEquals("C. Ferreira", contatoObtido.getSobrenome());
        assertEquals(LocalDate.of(2000, 7, 5), contatoObtido.getDataNascimento());
        assertEquals("98574-6323;91234-5678", contatoObtido.getTelefones());
        assertEquals("Amigo", contatoObtido.getGrauParentesco());
    }

    @Test
    void deveriaLancarExcecaoAoTentarBuscarUmContatoDaListaDeContatosDoUsuarioComIdInexistente(){
        assertThrows(ContatoNaoEncontradoException.class, () -> {
            contatoService.buscarPorId("nomeUsuario", 70L);
        });
    }

    @Test
    void deveriaExcluirUmContatoPorIdDaListaDeContatosDoUsuario(){

        var contatoQueDeveSerExcluido = new Contato(
                6L,
                "Aline",
                "Silva Ferreira",
                LocalDate.of(2005, 2, 6),
                "98574-6534;91234-1432",
                "Prima"
        );

        contatoService.excluirPorId("nomeUsuario", 6L);

        var contatosUsuario = usuarioTeste.getContatos();

        verify(usuarioService, times(1)).atualizar(usuarioTeste);
        assertFalse(contatosUsuario.contains(contatoQueDeveSerExcluido));
    }


    private Usuario usuarioTeste(){

        var contatos = new ArrayList<Contato>();
        contatos.add(new Contato(
                3L,
                "Nicolas",
                "C. Ferreira",
                LocalDate.of(2000, 7, 5),
                "98574-6323;91234-5678",
                "Amigo"
        ));

        contatos.add(new Contato(
                6L,
                "Aline",
                "Silva Ferreira",
                LocalDate.of(2005, 2, 6),
                "98574-6534;91234-1432",
                "Prima"
        ));

        contatos.add(new Contato(
                1L,
                "Daniel",
                "Alves Pereira",
                LocalDate.of(1998, 4, 2),
                "98574-6876;91234-2542",
                "Irmao"
        ));

        return new Usuario(
                9L,
                "Nome do Usuario Completo",
                "nomeUsuario",
                "usuario1234",
                contatos
        );
    }
}
