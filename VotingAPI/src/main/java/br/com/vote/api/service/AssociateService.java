package br.com.vote.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vote.api.exception.AssociateException;
import br.com.vote.api.form.AssociateForm;
import br.com.vote.api.model.Associate;
import br.com.vote.api.repository.AssociateRepository;

@Service
public class AssociateService {

	@Autowired
	private AssociateRepository associateRepository; 
	
	public void createNewAssociate(AssociateForm form) {
		Optional<Associate> optionalAssociate = associateRepository.findByCpf(form.getCpf());
		
		if(optionalAssociate.isPresent()) {
			throw new AssociateException("There is already a user registered with this CPF");
		}
		
		Associate associate = new Associate(form.getCpf());
		associateRepository.save(associate);
	}

}
