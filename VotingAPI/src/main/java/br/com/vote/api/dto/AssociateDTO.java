package br.com.vote.api.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class AssociateDTO extends RepresentationModel<AssociateDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;	
	private String cpf;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
	
}
