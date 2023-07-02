package br.com.nicoservices.contatospersistence.dto.contato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NovoContatoForm(
        @NotNull
        @NotBlank
        String nome,
        @NotNull
        @NotBlank
        String sobrenome,
        @NotNull
        LocalDate dataNascimento,
        @NotNull
        @NotBlank
        String telefones,
        @NotNull
        String grauParentesco
) {}
