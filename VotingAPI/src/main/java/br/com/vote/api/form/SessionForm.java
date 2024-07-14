package br.com.vote.api.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class SessionForm {

	@NotNull(message = "A data inicial da votação não pode ser vazia ou nula")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date start;
	@NotNull(message = "A data final da votação não pode ser vazia ou nula")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date end;
	@NotNull(message = "A sessão deve conter um identificador da pauta")
	private Long agendaId;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Long agendaId) {
		this.agendaId = agendaId;
	}

}
