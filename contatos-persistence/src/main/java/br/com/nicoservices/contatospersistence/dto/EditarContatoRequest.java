package br.com.nicoservices.contatospersistence.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record EditarContatoRequest(
        @NotNull
        Long id,
        @NotNull
        @NotBlank
        String nome,
        @NotNull
        @NotBlank
        @Email
        String email,
        @Pattern(regexp = "^([0-9]{9}|[0-9]{12}|[0-9]{14})$")
        String telefone1,
        @Pattern(regexp = "^(|[0-9]{9}|[0-9]{12}|[0-9]{14})$")
        String telefone2
) {}
