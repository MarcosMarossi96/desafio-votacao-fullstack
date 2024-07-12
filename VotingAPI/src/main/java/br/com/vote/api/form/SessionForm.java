package br.com.vote.api.form;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public class SessionForm {

	@NotNull
	private Date start;
	@NotNull
	private Date end;
	@NotNull
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
