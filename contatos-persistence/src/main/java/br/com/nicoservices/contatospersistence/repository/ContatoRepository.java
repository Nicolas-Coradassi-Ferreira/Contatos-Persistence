package br.com.nicoservices.contatospersistence.repository;

import br.com.nicoservices.contatospersistence.model.contato.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
