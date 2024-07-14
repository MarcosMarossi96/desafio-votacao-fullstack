package br.com.vote.api.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.vote.api.controller.AssociateController;
import br.com.vote.api.dto.AssociateDTO;
import br.com.vote.api.exception.AssociateException;
import br.com.vote.api.exception.ResourceNotFoundException;
import br.com.vote.api.form.AssociateForm;
import br.com.vote.api.mapper.CustomModelMapper;
import br.com.vote.api.model.Associate;
import br.com.vote.api.repository.AssociateRepository;

@Service
public class AssociateService {

	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private PagedResourcesAssembler<AssociateDTO> assembler;

	public void createNewAssociate(AssociateForm form) {
		Optional<Associate> optionalAssociate = associateRepository.findByCpf(form.getCpf());

		if (optionalAssociate.isPresent()) {
			throw new AssociateException("Já existe um associado cadastrado com este documento!");
		}

		Associate associate = CustomModelMapper.parseObject(form, Associate.class);
		associateRepository.save(associate);
	}

	public AssociateDTO findById(Long id) {
		Optional<Associate> associate = associateRepository.findById(id);

		if (!associate.isPresent()) {
			throw new ResourceNotFoundException("Não existe nenhum associado com o identificador: " + id);
		}

		AssociateDTO associateDTO = CustomModelMapper.parseObject(associate.get(), AssociateDTO.class);
		associateDTO.add(linkTo(methodOn(AssociateController.class).findById(id)).withSelfRel());

		return associateDTO;
	}

	public PagedModel<EntityModel<AssociateDTO>> findAll(Pageable pageable) {
		Page<Associate> associatePage = associateRepository.findAll(pageable);

		Page<AssociateDTO> associateDTO = associatePage.map(a -> CustomModelMapper.parseObject(a, AssociateDTO.class));

		associateDTO.stream().forEach(item -> {
			item.add(linkTo(methodOn(AssociateController.class).findById(item.getId())).withSelfRel());
		});

		Link link = linkTo(
				methodOn(AssociateController.class).findAll(pageable.getPageNumber(), pageable.getPageSize(), "asc"))
				.withSelfRel();

		return assembler.toModel(associateDTO, link);
	}

}
