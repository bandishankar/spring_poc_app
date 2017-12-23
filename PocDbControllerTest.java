package com.poc.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class PocDbControllerTest extends PocappTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAddData() throws Exception {
		mockMvc.perform(get("/db/add/?id=7&name=test1&age=35")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.age").exists()).andExpect(jsonPath("$.id").value(7))
				.andExpect(jsonPath("$.name").value("test1")).andExpect(jsonPath("$.age").value(35)).andDo(print());
	}

	@Test
	public void testFindInt() throws Exception {
		// add data to DB first
		mockMvc.perform(get("/db/add/?id=7&name=test1&age=35")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.age").exists()).andExpect(jsonPath("$.id").value(7))
				.andExpect(jsonPath("$.name").value("test1")).andExpect(jsonPath("$.age").value(35)).andDo(print());

		// check data with ID
		mockMvc.perform(get("/db/find/7")).andExpect(status().isOk()).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.age").exists())
				.andExpect(jsonPath("$.id").value(7)).andExpect(jsonPath("$.name").value("test1"))
				.andExpect(jsonPath("$.age").value(35)).andDo(print());
	}

	@Test
	public void testDelete() throws Exception {

		// add data to DB first
		mockMvc.perform(get("/db/add/?id=7&name=test1&age=35")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.age").exists()).andExpect(jsonPath("$.id").value(7))
				.andExpect(jsonPath("$.name").value("test1")).andExpect(jsonPath("$.age").value(35)).andDo(print());

		// check that data got deleted
		mockMvc.perform(get("/db/delete/7")).andExpect(status().isOk()).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").doesNotExist())
				.andExpect(jsonPath("$.id").doesNotExist())
				.andExpect(jsonPath("$.age").doesNotExist()).andDo(print());

	}
}
