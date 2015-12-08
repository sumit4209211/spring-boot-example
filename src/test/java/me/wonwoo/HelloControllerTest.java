package me.wonwoo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import me.wonwoo.account.Accounts;
import me.wonwoo.config.ConnectionSettings;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
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

	@Autowired
	private ConnectionSettings connectionSettings;

	@Test
	public void connectionTest() {
		System.out.println(connectionSettings.getUsername());
		System.out.println(connectionSettings.getRemoteAddress());

	}

	@Test
	public void createAccountTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");
		ResultActions createResult = createAccount(accounts);
		String respones = createResult.andReturn().getResponse().getContentAsString();
		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);
		ResultActions getResult = getAccount(resultAccounts.getId());
		getResult.andExpect(jsonPath("$.name", is("wonwoo")));
	}

	@Test
	public void createAccountDslTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");
		ResultActions createResult = createAccount(accounts);
		String respones = createResult.andReturn().getResponse().getContentAsString();
		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);
		ResultActions getResult = mockMvc.perform(get("/accountsdsl/" + resultAccounts.getId()).contentType(MediaType.APPLICATION_JSON));
		getResult.andDo(print());
		getResult.andExpect(status().isOk());
		getResult.andExpect(jsonPath("$.name", is("wonwoo")));
	}

	@Test
	public void createAccountsTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");
		ResultActions createResult = createAccount(accounts);

		Accounts accounts2 = new Accounts();
		accounts2.setName("young boss");
		accounts2.setPassword("young boss123");
		ResultActions createResult2 = createAccount(accounts2);

		ResultActions getResult = mockMvc.perform(get("/accounts").contentType(MediaType.APPLICATION_JSON));
		getResult.andDo(print());
		getResult.andExpect(status().isOk());

	}

	@Test
	public void updateAccountsTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");
		ResultActions createResult = createAccount(accounts);
		String respones = createResult.andReturn().getResponse().getContentAsString();
		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);

		resultAccounts.setName("update wonwoo");

		ResultActions updateResult = mockMvc.perform(put("/accounts/" + resultAccounts.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(resultAccounts)));
		updateResult.andDo(print());
		updateResult.andExpect(status().isOk());
		ResultActions getResult = getAccount(resultAccounts.getId());
		getResult.andExpect(jsonPath("$.name", is("update wonwoo")));

	}

	private ResultActions createAccount(Accounts accounts) throws Exception {
		ResultActions createResult = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accounts)));
		createResult.andDo(print());
		createResult.andExpect(status().isOk());
		return createResult;
	}

	private ResultActions getAccount(Long id) throws Exception {
		ResultActions getResult = mockMvc.perform(get("/accounts/" + id).contentType(MediaType.APPLICATION_JSON));
		getResult.andDo(print());
		getResult.andExpect(status().isOk());
		return getResult;
	}

}
