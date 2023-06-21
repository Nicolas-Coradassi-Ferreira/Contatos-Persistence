package br.com.nicoservices.contatospersistence.model.contato;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EditarContatoRequest {

    @NotNull
    private Long id;
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

    public EditarContatoRequest(Contato c) {
        id = c.getId();
        nome = c.getNome();
        email = c.getEmail();
        telefone1 = c.getTelefone1();
        telefone2 = c.getTelefone2();
    }
}
