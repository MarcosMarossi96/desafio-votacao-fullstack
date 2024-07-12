package br.com.vote.api.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class VoteDTO extends RepresentationModel<VoteDTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long sessionId;
	private Long yesVote;
	private Long noVote;

	public VoteDTO(Long sessionId, Long yesVote, Long noVote) {
		super();
		this.sessionId = sessionId;
		this.yesVote = yesVote;
		this.noVote = noVote;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getYesVote() {
		return yesVote;
	}

	public void setYesVote(Long yesVote) {
		this.yesVote = yesVote;
	}

	public Long getNoVote() {
		return noVote;
	}

	public void setNoVote(Long noVote) {
		this.noVote = noVote;
	}

}
