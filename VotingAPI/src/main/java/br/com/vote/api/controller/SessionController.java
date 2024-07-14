package br.com.vote.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import br.com.vote.api.dto.AssociateDTO;
import br.com.vote.api.dto.CreatedDTO;
import br.com.vote.api.dto.SessionDTO;
import br.com.vote.api.form.SessionForm;
import br.com.vote.api.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/session")
@Tag(name = "Session", description = "Endpoints to manage the voting session")
public class SessionController {
	
	private static Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	@Autowired
	private SessionService sessionService;

	@PostMapping
	@Operation(summary = "Create a new voting section", description = "Create a new voting section", tags = { "Session" }, responses = {
			@ApiResponse(responseCode = "201", description = "Success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CreatedDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "404", description = "Not Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public ResponseEntity<?> createNewAssociate(@Valid @RequestBody SessionForm form) {
		logger.info("Initializing the creation of a new session.");
		
		CreatedDTO newSession = sessionService.createNewSession(form);
		
		logger.info("Successfully finalizing session creation.");
		return new ResponseEntity<>(newSession, HttpStatus.CREATED);		
	}
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "Finds a section", description = "Finding a section", tags = { "Session" }, responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AssociateDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "404", description = "Not Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public SessionDTO findById(@PathVariable(name = "id") Long id) {
		logger.info("Initializing session search by ID.");
		
		SessionDTO associateDTO = sessionService.findById(id);
		
		logger.info("Successfully finalizing the session search by ID.");
		return associateDTO;
	}
	
}
