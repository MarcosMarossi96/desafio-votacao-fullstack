package br.com.vote.api.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.vote.api.dto.CreatedDTO;
import br.com.vote.api.dto.VoteDTO;
import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.form.AssociateForm;
import br.com.vote.api.form.SessionForm;
import br.com.vote.api.form.VoteForm;
import br.com.vote.api.integration.test.generic.GenericTestContainer;
import br.com.vote.api.mocks.AgendaMock;
import br.com.vote.api.mocks.AssociateMock;
import br.com.vote.api.mocks.SessionMock;
import br.com.vote.api.mocks.VoteMock;

public class VoteControllerTest  extends GenericTestContainer {
	
	private VoteMock mock = new VoteMock();
	private CreatedDTO createdSessionDTO;
	
	@BeforeAll
	public void setup() throws Exception {
		AgendaMock agendaMock = new AgendaMock();
		AgendaForm agendaForm = agendaMock.mockForm();
		
		MvcResult agendaResponse = mockMvc
				.perform(MockMvcRequestBuilders.post("/agenda").content(mapper.writeValueAsString(agendaForm))
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated()).andReturn();
		
		CreatedDTO createdAgendaDTO = mapper.readValue(agendaResponse.getResponse().getContentAsString(), CreatedDTO.class);	
		
		SessionMock sessionMock = new SessionMock();
		SessionForm sessionForm = sessionMock.mockForm(createdAgendaDTO.getId().intValue());
		
		MvcResult sessionResponse = mockMvc.perform(MockMvcRequestBuilders.post("/session").content(mapper.writeValueAsString(sessionForm))
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated()).andReturn();
		
		this.createdSessionDTO = mapper.readValue(sessionResponse.getResponse().getContentAsString(), CreatedDTO.class);
		
		AssociateMock associateMock = new AssociateMock();
		AssociateForm associateForm = associateMock.mockForm(5);

		mockMvc.perform(MockMvcRequestBuilders.post("/associate").content(mapper.writeValueAsString(associateForm))
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(1)
	public void testcreateNewVoteSuccess() throws Exception {
		VoteForm form = mock.mockForm(createdSessionDTO.getId().intValue());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/vote")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testcreateNewVoteWithError() throws Exception  {
		VoteForm form = mock.mockForm();
		form.setCpf("4548484");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/vote")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	@Order(3)
	public void testFindById() throws Exception {
		int sessionId = createdSessionDTO.getId().intValue();
		MvcResult responseObject = mockMvc.perform(MockMvcRequestBuilders.get("/vote/result/" + sessionId))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		
		VoteDTO vo = mapper.readValue(responseObject.getResponse().getContentAsString(), VoteDTO.class);		
		assertEquals(1L, vo.getYesVote());
		assertEquals(0, vo.getNoVote());
		assertEquals(createdSessionDTO.getId(), vo.getSessionId());
	}
	
	@Test
	@Order(4)
	public void testFindResultsNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/vote/result/" + 1000))
				.andDo(print()).andExpect(status().isNotFound());
	}

}
