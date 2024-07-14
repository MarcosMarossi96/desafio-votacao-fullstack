package br.com.vote.api.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VoteForm {

	@NotNull(message = "O voto não pode ser nulo")
	private boolean vote;
	@NotNull(message = "A votação deve acontecer dentro de uma sessão")
	private Long sessionId;
	@NotNull(message = "O cpf não pode ser nulo")
	@Size(min = 11, max = 11, message = "O CPF deve conter somente 11 digitos")
	private String cpf;
	@NotNull(message = "A data atual não pode ser nula")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date currentDate;

	public boolean isVote() {
		return vote;
	}

	public void setVote(boolean vote) {
		this.vote = vote;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}
	
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
