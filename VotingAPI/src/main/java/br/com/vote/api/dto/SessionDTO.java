package br.com.vote.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SessionDTO {

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date start;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date end;
	private String title;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
