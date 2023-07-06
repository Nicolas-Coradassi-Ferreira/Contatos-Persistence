package br.com.nicoservices.contatospersistence.model.usuario;

import br.com.nicoservices.contatospersistence.exception.ContatoNaoEncontradoException;
import br.com.nicoservices.contatospersistence.model.contato.Contato;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;


@Entity
@Table(
        name = "usuarios",
        uniqueConstraints = @UniqueConstraint(columnNames = "username")
)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_completo")
    private String nomeCompleto;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Contato> contatos;


    public Usuario(String nomeCompleto, String username, String password){
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.password = password;
    }


    public void adicionarContato(Contato c){
        this.contatos.add(c);
    }

    public void removerContatoPorId(Long id){
        this.contatos.removeIf(contato -> Objects.equals(contato.getId(), id));
    }

    public Contato getContatoPorId(Long id){
        return this.contatos
                .stream()
                .filter(contato -> Objects.equals(contato.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ContatoNaoEncontradoException("Não foi possível encontrar o contato!"));
    }

    public List<Contato> getContatos(){
        return unmodifiableList(this.contatos);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
