package br.com.vote.api.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.vote.api.controller.AgendaController;
import br.com.vote.api.dto.AgendaDTO;
import br.com.vote.api.dto.VoteDTO;
import br.com.vote.api.exception.ResourceNotFoundException;
import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.mapper.CustomModelMapper;
import br.com.vote.api.model.Agenda;
import br.com.vote.api.repository.AgendaRepository;
import br.com.vote.api.repository.VoteRepository;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private PagedResourcesAssembler<AgendaDTO> assembler;

	public void createNewAgenda(AgendaForm form) {
		Agenda agenda = new Agenda(form.getTitle(), form.getDescription());
		agendaRepository.save(agenda);
	}

	public PagedModel<EntityModel<AgendaDTO>> findAll(Pageable pageable) {
		Page<Agenda> agendaPage = agendaRepository.findAll(pageable);

		Page<AgendaDTO> agendaDTO = agendaPage.map(a -> CustomModelMapper.parseObject(a, AgendaDTO.class));
		
		List<Object[]> results = voteRepository.findAllResults();		
		Map<Long, VoteDTO> votes = getVotesByAgenda(results);		

		agendaDTO.stream().forEach(item -> {
			item.add(linkTo(methodOn(AgendaController.class).findById(item.getId())).withSelfRel());
			
			VoteDTO voteDTO = votes.get(item.getId());
			item.setVote(voteDTO);			
		});

		Link link = linkTo(
				methodOn(AgendaController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc"))
				.withSelfRel();

		return assembler.toModel(agendaDTO, link);
	}

	private Map<Long, VoteDTO> getVotesByAgenda(List<Object[]> results) {
		Map<Long, VoteDTO> result = new HashMap<>();
		
		results.forEach(item -> {
			Long sessionId = (Long) item[0];		
			Long agendaId = (Long) item[1];
			
            Long yesVotes = item[2] != null ? (Long) item[2] : 0;
            Long noVotes = item[3] != null ? (Long) item[3] : 0;
            
            result.put(agendaId, new VoteDTO(sessionId, yesVotes, noVotes));
		});
		
		return result;
	}

	public AgendaDTO findById(Long id) {
		Optional<Agenda> agenda = agendaRepository.findById(id);

		if (!agenda.isPresent()) {
			throw new ResourceNotFoundException("Não existe nenhuma pauta cadastrada com o identificador: " + id);
		}

		AgendaDTO agendaDTO = CustomModelMapper.parseObject(agenda.get(), AgendaDTO.class);
		agendaDTO.add(linkTo(methodOn(AgendaController.class).findById(id)).withSelfRel());

		return agendaDTO;
	}

}
