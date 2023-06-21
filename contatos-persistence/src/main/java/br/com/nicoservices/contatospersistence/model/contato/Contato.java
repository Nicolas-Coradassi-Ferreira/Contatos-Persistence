package br.com.nicoservices.contatospersistence.model.contato;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "contatos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Contato implements Comparable<Contato> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone1;
    private String telefone2;

    public Contato(NovoContatoRequest dadosNovoContato) {
        this.nome = dadosNovoContato.getNome();
        this.email = dadosNovoContato.getEmail();
        this.telefone1 = dadosNovoContato.getTelefone1();
        this.telefone2 = dadosNovoContato.getTelefone2();
    }

    public void atualizar(EditarContatoRequest contatoAtualizado) {
        this.nome = contatoAtualizado.getNome();
        this.email = contatoAtualizado.getEmail();
        this.telefone1 = contatoAtualizado.getTelefone1();
        this.telefone2 = contatoAtualizado.getTelefone2();
    }

    public void formatarTelefones() {
        telefone1 = formatarTelefone(telefone1);
        telefone2 = formatarTelefone(telefone2);
    }

    public String formatarTelefone(String telefone) {
        return switch (telefone.length()) {
            case 9 -> (telefone.substring(0, 5) + '-' + telefone.substring(5, 9));
            case 12 -> ('(' + telefone.substring(0, 3) + ") " + telefone.substring(3, 8) + '-' + telefone.substring(8, 12));
            case 14 -> ('+' + telefone.substring(0, 2) + " (" + telefone.substring(2, 5) + ") " + telefone.substring(5, 10) + '-' + telefone.substring(10, 14));
            default -> telefone;
        };
    }

    @Override
    public int compareTo(Contato c) {
        return this.nome.compareTo(c.nome);
    }
}
