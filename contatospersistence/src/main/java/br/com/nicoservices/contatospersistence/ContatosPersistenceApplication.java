package br.com.nicoservices.contatospersistence;

import br.com.nicoservices.contatospersistence.controls.UsuarioController;
import br.com.nicoservices.contatospersistence.models.usuario.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContatosPersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContatosPersistenceApplication.class, args);
	}

}
