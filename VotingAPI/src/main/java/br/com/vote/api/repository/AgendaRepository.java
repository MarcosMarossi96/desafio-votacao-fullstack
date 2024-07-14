package br.com.vote.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vote.api.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
