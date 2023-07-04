package br.com.nicoservices.contatospersistence.infra.security;

import br.com.nicoservices.contatospersistence.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Autenticador implements AuthenticationProvider {

    @Autowired
    private UsuarioService service;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        var username = auth.getName();
        var passwordAuth = passwordEncoder.encode((String) auth.getCredentials());

        var usuario = service.loadUserByUsername(username);

        if (usuario.getPassword().equals(passwordAuth)){
            auth.setAuthenticated(true);
            return auth;
        }

        auth.setAuthenticated(false);
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
