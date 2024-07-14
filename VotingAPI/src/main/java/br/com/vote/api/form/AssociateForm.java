package br.com.vote.api.form;

import jakarta.validation.constraints.Size;

public class AssociateForm {

	@Size(min = 11, max = 11, message = "O CPF deve conter somente 11 digitos")
	private String cpf;
	
	@Size(min = 3, max = 100, message = "O nome deve conter no mínimo 3 e no máximo 100 digitos")
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
