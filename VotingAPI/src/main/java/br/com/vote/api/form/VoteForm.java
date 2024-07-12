package br.com.vote.api.form;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class VoteForm {

	@NotNull
	private boolean vote;
	@NotNull
	private Long sessionId;
	@NotNull
	private Long associateId;
	@NotNull
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

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}
	
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
