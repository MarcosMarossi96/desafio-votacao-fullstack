package br.com.vote.api.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.vote.api.dto.AgendaDTO;
import br.com.vote.api.form.AgendaForm;
import br.com.vote.api.integration.test.generic.GenericTestContainer;
import br.com.vote.api.integration.test.wrapper.WrapperAgendaDTO;
import br.com.vote.api.mocks.AgendaMock;

public class AgendaControllerTest extends GenericTestContainer {

	private AgendaMock mock = new AgendaMock();

	@Test
	@Order(1)
	public void testcreateNewAgendaSuccess() throws Exception {
		AgendaForm form = mock.mockForm();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/agenda")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testCreateNewAgendaWithError() throws Exception  {
		AgendaForm form = mock.mockForm();
		form.setDescription(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/agenda")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	@Order(3)
	public void testFindAllAgenda() throws Exception {
		MvcResult responseObject = mockMvc.perform(MockMvcRequestBuilders.get("/agenda?page=0&limit=10"))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		
		String contentAsString = responseObject.getResponse().getContentAsString();
		WrapperAgendaDTO wrapper = mapper.readValue(contentAsString, WrapperAgendaDTO.class);
		
		assertNotNull(wrapper);
		
		List<AgendaDTO> agendas = wrapper.getEmbedded().getAgendas();
		
		assertFalse(agendas.isEmpty());
		
		AgendaDTO agendaDTO = agendas.get(0);
		assertEquals(1L, agendaDTO.getId());
	}

	@Test
	@Order(4)
	public void testFindById() throws Exception {
		MvcResult responseObject = mockMvc.perform(MockMvcRequestBuilders.get("/agenda/" + 1))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		
		AgendaDTO vo = mapper.readValue(responseObject.getResponse().getContentAsString(), AgendaDTO.class);
		
		assertEquals("Title10", vo.getTitle());		
		assertEquals(1, vo.getId());	
	}
	
	@Test
	@Order(5)
	public void testFindByIdNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/agenda/" + 1000))
				.andDo(print()).andExpect(status().isNotFound());
	}

}
