package br.com.vote.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vote.api.dto.AgendaDTO;
import br.com.vote.api.dto.CreatedSessionDTO;
import br.com.vote.api.dto.SessionDTO;
import br.com.vote.api.exception.ResourceNotFoundException;
import br.com.vote.api.exception.SessionException;
import br.com.vote.api.form.SessionForm;
import br.com.vote.api.mapper.CustomModelMapper;
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

	public CreatedSessionDTO createNewSession(SessionForm form) {
		Optional<Agenda> agenda = agendaRepository.findById(form.getAgendaId());

		if (!agenda.isPresent()) {
			throw new ResourceNotFoundException("Não existe nenhuma pauta cadastrada com o identificador: " + form.getAgendaId());
		}		
		
		Optional<Session> existsSession = sessionRepository.findByAgenda(agenda.get());
		
		if (existsSession.isPresent()) {
			throw new SessionException("Já foi criada uma sessão de votação para esta pauta!");
		}	

		Session session = sessionRepository.save(new Session(agenda.get(), form.getStart(), form.getEnd()));
		session.setAgenda(agenda.get());
		
		CreatedSessionDTO createdSession = CustomModelMapper.parseObject(session, CreatedSessionDTO.class);

		return createdSession;
	}

	public SessionDTO findById(Long id) {
		Optional<Session> session = sessionRepository.findById(id);

		if (!session.isPresent()) {
			throw new ResourceNotFoundException("Não existe nenhuma sessão de votação aberta nesta rota!");
		}
		
		SessionDTO sessionDTO = CustomModelMapper.parseObject(session.get(), SessionDTO.class);
		
		Agenda agenda = session.get().getAgenda();
		
		sessionDTO.setDescription(agenda.getDescription());
		sessionDTO.setTitle(agenda.getTitle());
						
		return sessionDTO;
	}

}
