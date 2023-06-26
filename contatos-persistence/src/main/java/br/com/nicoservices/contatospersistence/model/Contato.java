package br.com.nicoservices.contatospersistence.model;

import br.com.nicoservices.contatospersistence.dto.DadosEditarContato;
import br.com.nicoservices.contatospersistence.dto.DadosNovoContato;
import br.com.nicoservices.contatospersistence.util.TelefoneUtil;
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
        this.telefone1 = TelefoneUtil.formatar(dadosNovoContato.telefone1());
        this.telefone2 = TelefoneUtil.formatar(dadosNovoContato.telefone2());
    }


    public void atualizarDados(DadosEditarContato dadosAtualizados){
        this.nome = dadosAtualizados.nome();
        this.email = dadosAtualizados.email();
        this.telefone1 = TelefoneUtil.formatar(dadosAtualizados.telefone1());
        this.telefone2 = TelefoneUtil.formatar(dadosAtualizados.telefone2());
    }

    public DadosEditarContato toDadosEditarContato() {
        return new DadosEditarContato(
                this.id,
                this.nome,
                this.email,
                TelefoneUtil.desformatar(this.telefone1),
                TelefoneUtil.desformatar(this.telefone2));
    }

    @Override
    public int compareTo(Contato c) {
        return this.nome.compareTo(c.nome);
    }
}
