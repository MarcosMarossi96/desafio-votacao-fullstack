package br.com.vote.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vote.api.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

}
