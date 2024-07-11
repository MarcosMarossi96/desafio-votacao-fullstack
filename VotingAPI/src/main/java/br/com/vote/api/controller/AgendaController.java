package br.com.vote.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.api.dto.AgendaDTO;
import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.service.AgendaService;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaController {
	
	@Autowired
	private AgendaService agendaService;
	
	@PostMapping
	public ResponseEntity<?> createNewAssociate(@RequestBody AgendaForm form) {
		agendaService.createNewAgenda(form);
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<List<AgendaDTO>> findAll() {
		List<AgendaDTO> agenda = agendaService.findAll();
		return new ResponseEntity<>(agenda, HttpStatus.OK);		
	}
	
}
