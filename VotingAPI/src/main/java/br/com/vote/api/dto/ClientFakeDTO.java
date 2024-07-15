package br.com.vote.api.dto;

import br.com.vote.api.dto.constant.Status;

public class ClientFakeDTO {
	
	private Status status;

	public ClientFakeDTO() {
		
	}
	
	public ClientFakeDTO(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
