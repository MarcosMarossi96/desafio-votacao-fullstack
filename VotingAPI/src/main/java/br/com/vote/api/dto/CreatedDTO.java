package br.com.vote.api.dto;

public class CreatedDTO {
	
	private Long id;
	
	public CreatedDTO() {
		super();
	}
	
	public CreatedDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
