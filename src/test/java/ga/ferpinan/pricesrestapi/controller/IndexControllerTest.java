package ga.ferpinan.pricesrestapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
	private MockMvc mockMvc;

	@Test
	public void testIndexController() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isFound()).andReturn();
		assertEquals(HttpServletResponse.SC_FOUND, result.getResponse().getStatus());
	}
}
