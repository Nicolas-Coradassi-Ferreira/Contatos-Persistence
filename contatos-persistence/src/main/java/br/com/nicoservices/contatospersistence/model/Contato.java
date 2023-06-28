package br.com.nicoservices.contatospersistence.model;

import br.com.nicoservices.contatospersistence.dto.EditarContatoRequest;
import br.com.nicoservices.contatospersistence.dto.NovoContatoRequest;
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


    public Contato(NovoContatoRequest novoContatoRequest) {
        this.nome = novoContatoRequest.nome();
        this.email = novoContatoRequest.email();
        this.telefone1 = TelefoneUtil.formatar(novoContatoRequest.telefone1());
        this.telefone2 = TelefoneUtil.formatar(novoContatoRequest.telefone2());
    }


    public void atualizarDados(EditarContatoRequest dadosAtualizados){
        this.nome = dadosAtualizados.nome();
        this.email = dadosAtualizados.email();
        this.telefone1 = TelefoneUtil.formatar(dadosAtualizados.telefone1());
        this.telefone2 = TelefoneUtil.formatar(dadosAtualizados.telefone2());
    }

    public EditarContatoRequest toDadosEditarContato() {
        return new EditarContatoRequest(
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
