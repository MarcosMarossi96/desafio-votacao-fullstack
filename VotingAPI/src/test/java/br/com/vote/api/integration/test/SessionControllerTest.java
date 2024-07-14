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

import br.com.vote.api.dto.SessionDTO;
import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.form.SessionForm;
import br.com.vote.api.integration.test.generic.GenericTestContainer;
import br.com.vote.api.mocks.AgendaMock;
import br.com.vote.api.mocks.SessionMock;

public class SessionControllerTest extends GenericTestContainer {
	
	private SessionMock mock = new SessionMock();
	
	@BeforeAll
	public void setup() throws Exception {
		AgendaMock mock = new AgendaMock();
		AgendaForm mockForm = mock.mockForm();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/agenda")
				.content(mapper.writeValueAsString(mockForm))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(1)
	public void testcreateNewSessionSuccess() throws Exception {
		SessionForm form = mock.mockForm(1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/session")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testcreateNewSessionWithError() throws Exception  {
		SessionForm form = mock.mockForm();
		form.setAgendaId(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/session")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	@Order(3)
	public void testFindById() throws Exception {
		MvcResult responseObject = mockMvc.perform(MockMvcRequestBuilders.get("/session/" + 1))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		
		SessionDTO vo = mapper.readValue(responseObject.getResponse().getContentAsString(), SessionDTO.class);		
		assertEquals(1, vo.getId());
	}
	
	@Test
	@Order(4)
	public void testFindResultsNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/session/" + 1000))
				.andDo(print()).andExpect(status().isNotFound());
	}

}
