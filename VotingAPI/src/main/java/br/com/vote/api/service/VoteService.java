package br.com.vote.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vote.api.dto.VoteDTO;
import br.com.vote.api.exception.ResourceNotFoundException;
import br.com.vote.api.exception.VoteException;
import br.com.vote.api.form.VoteForm;
import br.com.vote.api.model.Associate;
import br.com.vote.api.model.Session;
import br.com.vote.api.model.Vote;
import br.com.vote.api.repository.AssociateRepository;
import br.com.vote.api.repository.SessionRepository;
import br.com.vote.api.repository.VoteRepository;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private AssociateRepository associateRepository;

	public void createNewVote(VoteForm form) {
		Optional<Associate> associate = associateRepository.findByCpf(form.getCpf());
		
		if(!associate.isPresent()) {
			throw new ResourceNotFoundException("Não existe nenhum associado castrado com este documento");
		}
		
		Optional<Session> session = sessionRepository.findById(form.getSessionId());
		
		if(!session.isPresent()) {
			throw new ResourceNotFoundException("Nenhuma sessão de votação foi aberta com o identificador: " + form.getSessionId());
		}
		
		if(form.getCurrentDate().after(session.get().getEnd())) {
			throw new VoteException("A sessão foi encerrada e não é mais possível votar!");
		}
		
		Optional<Vote> voteResult = voteRepository.findByAssociateAndSession(associate.get(), session.get());
		
		if(voteResult.isPresent()) {
			throw new VoteException("Já foi computado um voto com este documento na sessão " + form.getSessionId());
		}
				
		Vote vote = new Vote(session.get(), associate.get(), form.isVote());		
		voteRepository.save(vote);
	}

	public VoteDTO findResult(Long sessionId) {
		Optional<Session> session = sessionRepository.findById(sessionId);		
		
		if(!session.isPresent()) {
			throw new ResourceNotFoundException("Nenhuma sessão de votação foi aberta com o identificador: " + sessionId);
		}
		
		return voteRepository.findResultBySessionId(sessionId);
	}
}
