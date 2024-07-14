package br.com.vote.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vote.api.model.Agenda;
import br.com.vote.api.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
	
	Optional<Session> findByAgenda(Agenda agenda);

}
