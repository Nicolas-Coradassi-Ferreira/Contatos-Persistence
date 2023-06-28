package br.com.nicoservices.contatospersistence.dto;

import jakarta.validation.constraints.*;

public record NovoContatoRequest(
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
