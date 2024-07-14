package br.com.vote.api.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class AgendaDTO extends RepresentationModel<AgendaDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String title;
	private String description;
	
	private VoteDTO vote;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VoteDTO getVote() {
		return vote;
	}

	public void setVote(VoteDTO vote) {
		this.vote = vote;
	}

}
