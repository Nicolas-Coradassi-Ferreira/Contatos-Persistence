package br.com.nicoservices.contatospersistence.model;


import br.com.nicoservices.contatospersistence.dto.EditarContatoRequest;
import br.com.nicoservices.contatospersistence.dto.NovoContatoRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "contatos")
@NoArgsConstructor
public class Contato implements Comparable<Contato> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String telefones;
    @Column(name = "grau_parentesco")
    private String grauParentesco;


    public Contato(NovoContatoRequest dadosNovoContato) {
        this.nome = dadosNovoContato.nome();
        this.sobrenome = dadosNovoContato.sobrenome();
        this.dataNascimento = dadosNovoContato.dataNascimento();
        this.telefones = dadosNovoContato.telefones();
        this.grauParentesco = dadosNovoContato.grauParentesco();
    }


    public void atualizarDados(EditarContatoRequest dadosContatoAtualizados) {
        this.nome = dadosContatoAtualizados.nome();
        this.sobrenome = dadosContatoAtualizados.sobrenome();
        this.dataNascimento = dadosContatoAtualizados.dataNascimento();
        this.telefones = dadosContatoAtualizados.telefones();
        this.grauParentesco = dadosContatoAtualizados.grauParentesco();
    }

    public EditarContatoRequest toEditarContatoRequest() {
        return new EditarContatoRequest(
                this.id,
                this.nome,
                this.sobrenome,
                this.dataNascimento,
                this.telefones,
                this.grauParentesco
        );
    }

    @Override
    public int compareTo(Contato c) {
        return this.nome.compareTo(c.nome);
    }
}

