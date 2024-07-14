package br.com.vote.api.integration.test.wrapper;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.vote.api.dto.AgendaDTO;

public class AgendaEmbeddedDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("agendaDTOList")
	private List<AgendaDTO> agendas;
	
	public AgendaEmbeddedDTO() {
		
	}

	public List<AgendaDTO> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<AgendaDTO> agendas) {
		this.agendas = agendas;
	}

}
