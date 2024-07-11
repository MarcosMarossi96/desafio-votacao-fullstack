package br.com.vote.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vote.api.dto.AgendaDTO;
import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.model.Agenda;
import br.com.vote.api.repository.AgendaRepository;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository; 
	
	public void createNewAgenda(AgendaForm form) {
		Agenda agenda = new Agenda(form.getTitle(), form.getDescription());
		agendaRepository.save(agenda);
	}

	public List<AgendaDTO> findAll() {
		return null;
	}

}
