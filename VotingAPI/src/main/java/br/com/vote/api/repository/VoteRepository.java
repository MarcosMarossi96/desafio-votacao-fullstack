package br.com.vote.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.vote.api.dto.VoteDTO;
import br.com.vote.api.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	@Query("SELECT new br.com.vote.api.dto.VoteDTO(v.session.id, "
			+ "SUM(CASE WHEN v.vote = true THEN 1 ELSE 0 END), " + "SUM(CASE WHEN v.vote = false THEN 1 ELSE 0 END)) "
			+ "FROM Vote v WHERE v.session.id = :sessionId")
	VoteDTO findResultBySessionId(@Param("sessionId") Long sessionId);

}
