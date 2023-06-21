package br.com.nicoservices.contatospersistence.model.contato;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NovoContatoRequest {

    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @Pattern(regexp = "^([0-9]{9}|[0-9]{12}|[0-9]{14})$")
    private String telefone1;
    @Pattern(regexp = "^(|[0-9]{9}|[0-9]{12}|[0-9]{14})$")
    private String telefone2;

}
