package br.com.vote.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.api.form.AssociateForm;
import br.com.vote.api.service.AssociateService;

@RestController
@RequestMapping(value = "/associate")
public class AssociateController {
	
	@Autowired
	private AssociateService associateService;
	
	@PostMapping
	public ResponseEntity<?> createNewAssociate(@RequestBody AssociateForm associateForm) {
		associateService.createNewAssociate(associateForm);
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
}
