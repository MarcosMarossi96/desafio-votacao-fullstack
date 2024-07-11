package br.com.vote.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vote.api.model.Associate;

public interface AssociateRepository extends JpaRepository<Associate, Long>{

	Optional<Associate> findByCpf(String cpf);
	
}
