package br.com.vote.api.integration.test.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WrapperAgendaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("_embedded")
	private AgendaEmbeddedDTO embedded;
	
	public WrapperAgendaDTO() {
		
	}

	public AgendaEmbeddedDTO getEmbedded() {
		return embedded;
	}

	public void setEmbedded(AgendaEmbeddedDTO embedded) {
		this.embedded = embedded;
	}	

}
