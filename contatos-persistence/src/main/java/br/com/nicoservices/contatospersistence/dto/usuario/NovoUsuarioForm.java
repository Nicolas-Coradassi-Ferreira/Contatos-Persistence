package br.com.nicoservices.contatospersistence.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NovoUsuarioForm(
        @NotNull
        @NotBlank
        String nomeCompleto,
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        @Pattern(regexp = "^\\w{8,}$")
        String password
) {
}
