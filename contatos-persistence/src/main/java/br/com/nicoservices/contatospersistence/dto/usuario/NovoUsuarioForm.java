package br.com.nicoservices.contatospersistence.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NovoUsuarioForm(
        @NotNull
        @NotBlank
        String nomeCompleto,
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        String password
) {
}
