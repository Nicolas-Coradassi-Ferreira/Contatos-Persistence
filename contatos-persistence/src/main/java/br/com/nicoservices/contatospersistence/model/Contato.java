package br.com.nicoservices.contatospersistence.model;

import br.com.nicoservices.contatospersistence.dto.DadosEditarContato;
import br.com.nicoservices.contatospersistence.dto.DadosNovoContato;
import br.com.nicoservices.contatospersistence.util.TelefoneUtils;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "contatos")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Contato implements Comparable<Contato> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone1;
    private String telefone2;


    public Contato(DadosNovoContato dadosNovoContato) {
        this.nome = dadosNovoContato.nome();
        this.email = dadosNovoContato.email();
        this.telefone1 = TelefoneUtils.formatar(dadosNovoContato.telefone1());
        this.telefone2 = TelefoneUtils.formatar(dadosNovoContato.telefone2());
    }

    public Contato(DadosEditarContato dadosContatoAtualizado) {
        this.id = dadosContatoAtualizado.id();
        this.nome = dadosContatoAtualizado.nome();
        this.email = dadosContatoAtualizado.email();
        this.telefone1 = TelefoneUtils.formatar(dadosContatoAtualizado.telefone1());
        this.telefone2 = TelefoneUtils.formatar(dadosContatoAtualizado.telefone2());
    }


    public DadosEditarContato toDadosEditarContato() {
        return new DadosEditarContato(
                this.id,
                this.nome,
                this.email,
                TelefoneUtils.desformatar(this.telefone1),
                TelefoneUtils.desformatar(this.telefone2));
    }

    @Override
    public int compareTo(Contato c) {
        return this.nome.compareTo(c.nome);
    }
}
