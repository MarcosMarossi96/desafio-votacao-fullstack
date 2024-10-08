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
import br.com.vote.api.dto.AssociateDTO;
import br.com.vote.api.dto.ClientFakeDTO;
import br.com.vote.api.form.AssociateForm;
import br.com.vote.api.form.ClientFakeForm;
import br.com.vote.api.service.AssociateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/associate")
@Tag(name = "Associate", description = "Endpoints for associates")
public class AssociateController {
	
	private static Logger logger = LoggerFactory.getLogger(AssociateController.class);
	
	@Autowired
	private AssociateService associateService;
	
	@PostMapping
	@Operation(summary = "Create a new associate", description = "Create a new associate", tags = { "Associate" }, responses = {
			@ApiResponse(responseCode = "201", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public ResponseEntity<?> createNewAssociate(@Valid @RequestBody AssociateForm associateForm) {
		logger.info("Initializing the creation of a new associate.");
		
		associateService.createNewAssociate(associateForm);
		
		logger.info("Successfully finalizing associate creation.");
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	
	@GetMapping
	@Operation(summary = "Finds all associates", description = "Finds all associates", tags = { "Associate" }, responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AssociateDTO.class))) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public PagedModel<EntityModel<AssociateDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		logger.info("Initiating the search for registered associates.");
		
		var sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		PagedModel<EntityModel<AssociateDTO>> associates = associateService.findAll(pageable);
		
		logger.info("Successfully finalizing the associates searches.");
		return associates;
	}

	@GetMapping(path = "/{id}")
	@Operation(summary = "Finds a associate", description = "Finding an associate", tags = { "Associate" }, responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AssociateDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "404", description = "Not Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public AssociateDTO findById(@PathVariable(name = "id") Long id) {
		logger.info("Initializing associate search by ID.");
		
		AssociateDTO associateDTO = associateService.findById(id);
		
		logger.info("Successfully finalizing the associate search by ID.");
		return associateDTO;
	}
	
	@PostMapping(path = "/client")
	@Operation(summary = "Client for CPF consultation", description = "Client for CPF consultation", tags = { "Associate" }, responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientFakeDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorMessage.class)) }), })
	public ClientFakeDTO consultation(@RequestBody ClientFakeForm form) {
		logger.info("Initializing the validation if the CPF is valid.");
		
		ClientFakeDTO associateDTO = associateService.consultation(form);
		
		logger.info("Finalizing the validation if the CPF is valid");
		return associateDTO;
	}
	
}
