package br.com.vote.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.api.dto.VoteDTO;
import br.com.vote.api.form.VoteForm;
import br.com.vote.api.service.VoteService;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@PostMapping
	public ResponseEntity<?> createNewVote(@RequestBody VoteForm form) {
		voteService.createNewVote(form);
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@GetMapping(path = "/result/{sessionId}")
	public ResponseEntity<VoteDTO> findResult(@PathVariable Long sessionId) {
		VoteDTO voteDTO = voteService.findResult(sessionId);
		return new ResponseEntity<>(voteDTO, HttpStatus.OK);		
	}
	
}
