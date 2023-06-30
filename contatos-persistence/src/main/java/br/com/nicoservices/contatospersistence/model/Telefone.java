package br.com.nicoservices.contatospersistence.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contatos_telefones")
@NoArgsConstructor
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer codigoPais;
    private Integer ddd;
    private Integer numero;
}
