package me.wonwoo;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.wonwoo.account.Accounts;
import me.wonwoo.config.ConnectionSettings;
import me.wonwoo.config.oauth2.AccessToken;

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

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	public static final String username = "wonwoo";

	public static final String password = "pwadmin";

	public String accessToken;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
		String authorization = "Basic " + new String(Base64Utils.encode("myapp:XX001".getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";
		String content = mockMvc
				.perform(post("/oauth/token").header("Authorization", authorization)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", username)
						.param("password", password).param("grant_type", "password").param("scope", "read write")
						.param("client_id", "myapp").param("client_secret", "XX001"))
				.andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read write")))).andDo(print()).andReturn().getResponse()
				.getContentAsString();
		AccessToken accessToken = objectMapper.readValue(content, AccessToken.class);
		this.accessToken = accessToken.getAccess_token();
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
		accounts.setName("won");
		accounts.setPassword("won");
		ResultActions createResult = createAccount(accounts);
		createResult.andExpect(status().isBadRequest());
	}

	@Test
	public void createAccountsTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo12388");
		accounts.setPassword("wonwoo123");
		ResultActions createResult = createAccount(accounts);
		createResult.andExpect(status().isOk());

		Accounts accounts2 = new Accounts();
		accounts2.setName("young boss");
		accounts2.setPassword("young boss123");
		ResultActions createResult2 = createAccount(accounts2);
		createResult2.andExpect(status().isOk());
	}

	@Test
	public void updateAccountsTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwooUpdate");

		ResultActions updateResult = updateAccount(1L, accounts);
		updateResult.andExpect(status().isOk());
		ResultActions getResult = getAccount(1L);
		getResult.andExpect(status().isOk());
		getResult.andExpect(jsonPath("$.name", is("wonwooUpdate")));
	}
	
	@Test
	public void updateAccountsNotFoundExceptionTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwooUpdate");
		ResultActions updateResult = updateAccount(1L, accounts);
		updateResult.andExpect(status().isOk());
		ResultActions getResult = getAccount(100L);
		getResult.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteAccountsTest() throws Exception {
		ResultActions deleteResult = mockMvc.perform(delete("/accounts/2")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON));
		deleteResult.andDo(print());
		deleteResult.andExpect(status().isOk());
	}
	
	@Test
	public void deleteAccountsNotFoundTest() throws Exception {
		ResultActions deleteResult = mockMvc.perform(delete("/accounts/100")
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON));
		deleteResult.andDo(print());
		deleteResult.andExpect(status().isBadRequest());
	}

	@Test
	public void getAccountnotFoundExceptionTest() throws Exception {
		ResultActions actions = getAccount(100L);
		actions.andExpect(status().isBadRequest());
		String response = actions.andReturn().getResponse().getContentAsString();
		System.out.println(response);
	}

	@Test
	public void getAccountDuplicateExceptionTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");
		ResultActions createResult = createAccount(accounts);
		createResult.andExpect(status().isBadRequest());
	}

	private ResultActions createAccount(Accounts accounts) throws Exception {
		ResultActions createResult = mockMvc.perform(post("/accounts").header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accounts)));
		createResult.andDo(print());
		// createResult.andExpect(status().isOk());
		return createResult;
	}

	private ResultActions getAccount(Long id) throws Exception {
		ResultActions getResult = mockMvc.perform(get("/accounts/" + id)
				.header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON));
		getResult.andDo(print());
		return getResult;
	}

	private ResultActions updateAccount(Long id, Accounts accounts) throws Exception {
		ResultActions updateResult = mockMvc.perform(put("/accounts/" + id).header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accounts)));
		updateResult.andDo(print());
		return updateResult;
	}

	@Test
	public void getAccountScopeTest() throws Exception {
		getAccount(1L).andExpect(status().isOk()).andDo(print());
	}
	

	// @Test
	// public void createAccountDslTest() throws Exception {
	// Accounts accounts = new Accounts();
	// accounts.setName("wonwoo");
	// accounts.setPassword("wonwoo123");
	// ResultActions createResult = createAccount(accounts);
	// createResult.andExpect(status().isOk());
	// String respones =
	// createResult.andReturn().getResponse().getContentAsString();
	// Accounts resultAccounts = objectMapper.readValue(respones,
	// Accounts.class);
	// ResultActions getResult = mockMvc
	// .perform(get("/accountsdsl/" +
	// resultAccounts.getId()).contentType(MediaType.APPLICATION_JSON));
	// getResult.andDo(print());
	// getResult.andExpect(status().isOk());
	// getResult.andExpect(jsonPath("$.name", is("wonwoo")));
	// }
}
