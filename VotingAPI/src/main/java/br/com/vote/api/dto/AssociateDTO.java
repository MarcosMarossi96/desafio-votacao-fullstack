package br.com.vote.api.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class AssociateDTO extends RepresentationModel<AssociateDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String cpf;
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
