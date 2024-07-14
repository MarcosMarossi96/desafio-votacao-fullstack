package br.com.vote.api.integration.test.wrapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WrapperAssociateDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("_embedded")
	private AssociateEmbeddedDTO embedded;
	
	public WrapperAssociateDTO() {
		
	}

	public AssociateEmbeddedDTO getEmbedded() {
		return embedded;
	}

	public void setEmbedded(AssociateEmbeddedDTO embedded) {
		this.embedded = embedded;
	}	

}
