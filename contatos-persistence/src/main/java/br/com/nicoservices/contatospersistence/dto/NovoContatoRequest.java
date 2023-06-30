package br.com.nicoservices.contatospersistence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NovoContatoRequest(
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
        @NotBlank
        String grauParentesco
) {}
