package br.com.vote.api.integration.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.vote.api.dto.AssociateDTO;
import br.com.vote.api.form.AssociateForm;
import br.com.vote.api.integration.test.generic.GenericTestContainer;
import br.com.vote.api.integration.test.wrapper.WrapperAssociateDTO;
import br.com.vote.api.mocks.AssociateMock;

public class AssociateControllerTest extends GenericTestContainer {
	
	private AssociateMock mock = new AssociateMock();

	@Test
	@Order(1)
	public void testcreateNewAssociateSuccess() throws Exception {
		AssociateForm form = mock.mockForm();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/associate")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testCreateNewAssociateWithError() throws Exception  {
		AssociateForm form = mock.mockForm();
		form.setCpf("12484874");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/associate")
				.content(mapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	@Order(3)
	public void testFindAllAssociates() throws Exception {
		MvcResult responseObject = mockMvc.perform(MockMvcRequestBuilders.get("/associate?page=0&limit=10"))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		
		String contentAsString = responseObject.getResponse().getContentAsString();
		WrapperAssociateDTO wrapper = mapper.readValue(contentAsString, WrapperAssociateDTO.class);
		
		assertNotNull(wrapper);
		
		List<AssociateDTO> associates = wrapper.getEmbedded().getAssociates();
		
		assertFalse(associates.isEmpty());
		
		for (AssociateDTO associateDTO : associates) {
			assertTrue(associateDTO.getName().contains("Associate"));
		}		
	}

	@Test
	@Order(4)
	public void testFindById() throws Exception {
		MvcResult responseObject = mockMvc.perform(MockMvcRequestBuilders.get("/associate/" + 1))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		
		AssociateDTO vo = mapper.readValue(responseObject.getResponse().getContentAsString(), AssociateDTO.class);
		
		assertEquals("21212121210", vo.getCpf());		
		assertEquals(1, vo.getId());	
	}
	
	@Test
	@Order(5)
	public void testFindByIdNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/associate/" + 1000))
				.andDo(print()).andExpect(status().isNotFound());
	}

}
