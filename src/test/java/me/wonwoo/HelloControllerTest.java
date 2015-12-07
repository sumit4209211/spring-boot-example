package me.wonwoo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import me.wonwoo.hello.Accounts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class HelloControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void test() throws Exception {
		ResultActions result = mockMvc.perform(get("/helloWorld").contentType(MediaType.APPLICATION_JSON));
		result.andDo(print());
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id", is("wonwoo")));
		// System.out.println("git test");
	}

	@Test
	public void createAccountTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		ResultActions createResult = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accounts)));
		createResult.andDo(print());
		createResult.andExpect(status().isOk());
		String respones = createResult.andReturn().getResponse().getContentAsString();
		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);
		ResultActions getResult = mockMvc.perform(get("/accounts/" + resultAccounts.getId()).contentType(MediaType.APPLICATION_JSON));
		getResult.andDo(print());
		getResult.andExpect(status().isOk());
		getResult.andExpect(jsonPath("$.name", is("wonwoo")));

	}
	
	@Test
	public void createAccountDslTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		ResultActions createResult = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accounts)));
		createResult.andDo(print());
		createResult.andExpect(status().isOk());
		String respones = createResult.andReturn().getResponse().getContentAsString();
		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);
		ResultActions getResult = mockMvc.perform(get("/accountsdsl/" + resultAccounts.getId()).contentType(MediaType.APPLICATION_JSON));
		getResult.andDo(print());
		getResult.andExpect(status().isOk());
		getResult.andExpect(jsonPath("$.name", is("wonwoo")));
		
	}
}
