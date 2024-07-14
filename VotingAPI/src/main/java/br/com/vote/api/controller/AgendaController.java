package br.com.vote.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vote.api.controller.advice.ApiErrorMessage;
import br.com.vote.api.dto.AgendaDTO;
import br.com.vote.api.dto.CreatedDTO;
import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/agenda")
@Tag(name = "Agenda", description = "Endpoints for the voting agenda")
public class AgendaController {

	private static Logger logger = LoggerFactory.getLogger(AgendaController.class);
	
	@Autowired
	private AgendaService agendaService;

	@PostMapping
	@Operation(summary = "Create a new agenda", description = "Create a new agenda", tags = { "Agenda" }, responses = {
			@ApiResponse(responseCode = "201", description = "Success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CreatedDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),			
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public ResponseEntity<?> createNewAgenda(@Valid @RequestBody AgendaForm form) {
		logger.info("Initializing the creation of a new agenda.");
		
		CreatedDTO createdDTO = agendaService.createNewAgenda(form);
		
		logger.info("Successfully finalizing agenda creation.");
		return new ResponseEntity<>(createdDTO, HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(summary = "Finds all agendas", description = "Finds all agendas", tags = { "Agenda" }, responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AgendaDTO.class))) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public PagedModel<EntityModel<AgendaDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		logger.info("Initiating the search for registered agendas.");
		
		var sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
		PagedModel<EntityModel<AgendaDTO>> agendas = agendaService.findAll(pageable);

		logger.info("Successfully finalizing the agenda searches.");
		return agendas;
	}

	@GetMapping(path = "/{id}")
	@Operation(summary = "Finds a agenda", description = "Finding an agenda", tags = { "Agenda" }, responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AgendaDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "404", description = "Not Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public AgendaDTO findById(@PathVariable(name = "id") Long id) {		
		logger.info("Initializing agenda search by ID.");
		
		AgendaDTO agendaDTO = agendaService.findById(id);
		
		logger.info("Successfully finalizing the agenda search by ID.");
		return agendaDTO;
	}

}
