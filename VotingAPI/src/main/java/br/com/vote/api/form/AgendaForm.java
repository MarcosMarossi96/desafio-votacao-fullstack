package br.com.vote.api.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AgendaForm {

	@NotBlank(message = "O título não pode ser vazio")
	@Size(min = 1, max = 100, message = "O título deve conter no máximo 100 caracteres.")
	private String title;

	@NotBlank(message = "A descrição não pode ser vazia")
	@Size(min = 1, max = 255, message = "A descrição deve conter no máximo 255 caracteres.")
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
