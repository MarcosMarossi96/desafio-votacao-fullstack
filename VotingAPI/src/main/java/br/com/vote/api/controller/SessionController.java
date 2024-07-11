package br.com.vote.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.api.form.SessionForm;
import br.com.vote.api.model.Session;
import br.com.vote.api.service.SessionService;

@RestController
@RequestMapping(value = "/session")
public class SessionController {
	
	@Autowired
	private SessionService sessionService;

	@PostMapping
	public ResponseEntity<?> createNewAssociate(@RequestBody SessionForm form) {
		Session newSession = sessionService.createNewSession(form);
		return new ResponseEntity<>(newSession, HttpStatus.CREATED);		
	}
	
	// TODO Encerrar uma sessão
	
}
