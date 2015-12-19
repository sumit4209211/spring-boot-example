package me.wonwoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wonwoo.account.Accounts;
import me.wonwoo.account.AccountsService;
import me.wonwoo.config.ConnectionSettings;
import me.wonwoo.config.oauth2.AccessToken;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
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
		createResult.andExpect(status().isOk());
		String respones = createResult.andReturn().getResponse().getContentAsString();
		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);
		ResultActions getResult = getAccount(resultAccounts.getId());
		getResult.andExpect(status().isOk());
		getResult.andExpect(jsonPath("$.name", is("wonwoo")));
	}

//	@Test
//	public void createAccountDslTest() throws Exception {
//		Accounts accounts = new Accounts();
//		accounts.setName("wonwoo");
//		accounts.setPassword("wonwoo123");
//		ResultActions createResult = createAccount(accounts);
//		createResult.andExpect(status().isOk());
//		String respones = createResult.andReturn().getResponse().getContentAsString();
//		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);
//		ResultActions getResult = mockMvc
//				.perform(get("/accountsdsl/" + resultAccounts.getId()).contentType(MediaType.APPLICATION_JSON));
//		getResult.andDo(print());
//		getResult.andExpect(status().isOk());
//		getResult.andExpect(jsonPath("$.name", is("wonwoo")));
//	}

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

		ResultActions updateResult = mockMvc.perform(put("/accounts/" + resultAccounts.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(resultAccounts)));
		updateResult.andDo(print());
		updateResult.andExpect(status().isOk());
		ResultActions getResult = getAccount(resultAccounts.getId());
		getResult.andExpect(status().isOk());
		getResult.andExpect(jsonPath("$.name", is("update wonwoo")));
	}

	@Test
	public void deleteAccountsTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");
		ResultActions createResult = createAccount(accounts);
		createResult.andExpect(status().isOk());
		String respones = createResult.andReturn().getResponse().getContentAsString();
		Accounts resultAccounts = objectMapper.readValue(respones, Accounts.class);

		ResultActions deleteResult = mockMvc.perform(delete("/accounts/" + resultAccounts.getId())
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(resultAccounts)));
		deleteResult.andDo(print());
		deleteResult.andExpect(status().isOk());
	}

	@Test
	public void getAccountnotFoundExceptionTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");
		createAccount(accounts);

		ResultActions actions = getAccount(2L);
		actions.andExpect(status().isBadRequest());
		String response = actions.andReturn().getResponse().getContentAsString();
		System.out.println(response);
	}

	@Test
	public void createAccount() throws Exception {
		Accounts creatDto = new Accounts();
		creatDto.setName("wonwoo");
		creatDto.setPassword("wonwoo123");

		ResultActions result = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creatDto)));

		result.andDo(print());
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.name", is("wonwoo")));

		result = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(creatDto)));

		result.andDo(print());
		result.andExpect(status().isBadRequest());
		// result.andExpect(jsonPath("$.code",
		// is("duplicated.username.exception")));
	}

	@Autowired
	private AccountsService accountsService;

	@Test
	public void getAccountDuplicateExceptionTest() throws Exception {
		Accounts accounts = new Accounts();
		accounts.setName("wonwoo");
		accounts.setPassword("wonwoo123");

		ResultActions createResult = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(accounts)));
		createResult.andDo(print());

		createResult = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(accounts)));
		createResult.andDo(print());
		createResult.andExpect(status().isBadRequest());
		// createResult.andExpect(status().isOk());

		// bug?
		// javax.servpringframework.web.servlet.view.InternalResourceView.prepareForRendering(InternalResourceView.java:205)
		// ResultActions createResult =
		// mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accounts)));
		// ResultActions createResult = mockMvc.perform(poslet.ServletException:
		// Circular view path [accounts]: would dispatch back to the current
		// handler URL [/accounts] again. Check your ViewResolver setup! (Hint:
		// This may be the result of an unspecified view, due to default view
		// name generation.)
		// at
		// org.st("/accounts/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(accounts)));
		// Accounts accounts1 = new Accounts();
		// accounts1.setName("wonwoo");
		// accounts1.setPassword("wonwoo555");
		// ResultActions createResult1 = createAccount(accounts1);
		//

	}

	private ResultActions createAccount(Accounts accounts) throws Exception {
		ResultActions createResult = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(accounts)));
		createResult.andDo(print());
		// createResult.andExpect(status().isOk());
		return createResult;
	}

	private ResultActions getAccount(Long id) throws Exception {
		String accessToken = getAccessToken("wonwoo","pwadmin");
		ResultActions getResult = mockMvc.perform(get("/accounts/" + id).header("Authorization", "Bearer " + accessToken).contentType(MediaType.APPLICATION_JSON));
		getResult.andDo(print());
		// getResult.andExpect(status().isOk());
		return getResult;
	}

	private String getAccessToken(String username, String password) throws Exception {
		String authorization = "Basic " + new String(Base64Utils.encode("myapp:XX001".getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";
		String content = mockMvc
				.perform(
						post("/oauth/token")
								.header("Authorization", authorization)
								.contentType(
										MediaType.APPLICATION_FORM_URLENCODED)
								.param("username", username)
								.param("password", password)
								.param("grant_type", "password")
								.param("scope", "read")
								.param("client_id", "myapp")
								.param("client_secret", "XX001"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read"))))
				.andDo(print())
				.andReturn().getResponse().getContentAsString();

		AccessToken accessToken = objectMapper.readValue(content, AccessToken.class);
		return accessToken.getAccess_token();
	}

	@Test
	public void getAccountScopeTest() throws Exception {
		getAccount(1L).andExpect(status().isForbidden())
					  .andDo(print());
	}

}
