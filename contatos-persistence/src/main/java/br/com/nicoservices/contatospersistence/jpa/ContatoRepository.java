package br.com.nicoservices.contatospersistence.jpa;

import br.com.nicoservices.contatospersistence.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
