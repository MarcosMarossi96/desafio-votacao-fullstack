package br.com.vote.api.form;

import jakarta.validation.constraints.Size;

public class AssociateForm {

	@Size(min = 11, max = 11, message = "The CPF must contain 11 digits")
	private String cpf;
	
	@Size(min = 3, max = 100, message = "The name must contain between 3 and 100 digits")
	private String name;

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
