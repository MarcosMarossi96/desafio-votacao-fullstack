package br.com.vote.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vote.api.dto.AgendaDTO;
import br.com.vote.api.exception.ResourceNotFoundException;
import br.com.vote.api.form.SessionForm;
import br.com.vote.api.model.Agenda;
import br.com.vote.api.model.Session;
import br.com.vote.api.repository.AgendaRepository;
import br.com.vote.api.repository.SessionRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository; 
	
	@Autowired
	private AgendaRepository agendaRepository; 

	public List<AgendaDTO> findAll() {
		return null;
	}

	public Session createNewSession(SessionForm form) {		
		Optional<Agenda> agenda = agendaRepository.findById(form.getAgendaId());
		
		if(!agenda.isPresent()) {
			throw new ResourceNotFoundException("There is no agenda with the id " + form.getAgendaId());
		}
				
		Session session = new Session(agenda.get(), form.getStart(), form.getEnd());
		
		return sessionRepository.save(session);		
	}
	
}
