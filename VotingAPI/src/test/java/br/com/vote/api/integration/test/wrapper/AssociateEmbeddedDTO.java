package br.com.vote.api.integration.test.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vote.api.dto.AssociateDTO;

public class AssociateEmbeddedDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("associateDTOList")
	private List<AssociateDTO> associates;

	public AssociateEmbeddedDTO() {

	}

	public List<AssociateDTO> getAssociates() {
		return associates;
	}

	public void setAssociates(List<AssociateDTO> associates) {
		this.associates = associates;
	}

}
