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

import br.com.vote.api.controller.advice.ApiErrorMessage;
import br.com.vote.api.dto.VoteDTO;
import br.com.vote.api.form.VoteForm;
import br.com.vote.api.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vote")
@Tag(name = "Vote", description = "Endpois to manage the vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@PostMapping
	@Operation(summary = "Create a new vote", description = "Create a new vote", tags = { "Vote" }, responses = {
			@ApiResponse(responseCode = "201", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "404", description = "Not Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public ResponseEntity<?> createNewVote(@Valid @RequestBody VoteForm form) {
		voteService.createNewVote(form);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/result/{sessionId}")
	@Operation(summary = "Search the voting results of an open session", description = "Search the voting results of an open session", tags = { "Vote" }, responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = VoteDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "404", description = "Not Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public ResponseEntity<VoteDTO> findResult(@PathVariable Long sessionId) {
		VoteDTO voteDTO = voteService.findResult(sessionId);
		return new ResponseEntity<>(voteDTO, HttpStatus.OK);		
	}
	
}
