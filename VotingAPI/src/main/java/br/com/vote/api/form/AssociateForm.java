package br.com.vote.api.form;

import jakarta.validation.constraints.Size;

public class AssociateForm {

	@Size(min = 11, max = 11, message = "The CPF must contain 11 digits")
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
