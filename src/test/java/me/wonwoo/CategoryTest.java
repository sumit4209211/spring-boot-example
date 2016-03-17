package me.wonwoo;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.wonwoo.blog.category.Category;
import me.wonwoo.blog.post.Post;
import me.wonwoo.config.oauth2.AccessToken;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.JVM)
public class CategoryTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	private static final String username = "aoruqjfu@gmail.com";

	private static final String password = "pwadmin";

	private String accessToken;

	private String client_id = "wonwooapp";
	private String client_secret = "XX0000001";

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
		String authorization = "Basic " + new String(Base64Utils.encode((client_id + ":" + client_secret).getBytes()));
		String contentType = MediaType.APPLICATION_JSON + ";charset=UTF-8";
		String content = mockMvc
				.perform(post("/oauth/token").header("Authorization", authorization)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("username", username)
						.param("password", password).param("grant_type", "password").param("scope", "read write")
						.param("client_id", client_id).param("client_secret", client_secret))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.access_token", is(notNullValue())))
				.andExpect(jsonPath("$.token_type", is(equalTo("bearer"))))
				.andExpect(jsonPath("$.refresh_token", is(notNullValue())))
				.andExpect(jsonPath("$.expires_in", is(greaterThan(4000))))
				.andExpect(jsonPath("$.scope", is(equalTo("read write")))).andDo(print()).andReturn().getResponse()
				.getContentAsString();
		AccessToken accessToken = objectMapper.readValue(content, AccessToken.class);
		this.accessToken = accessToken.getAccess_token();
	}
	
	@Test
	public void categoryCreateTest() throws Exception{
		Category category = new Category();
		category.setName("카테고리");
		ResultActions createResult = mockMvc.perform(post("/category").header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(category)));
		createResult.andDo(print());
		createResult.andExpect(status().isCreated());
	}
	
	@Test
	public void getCategoryTest() throws Exception{
		ResultActions createResult = mockMvc.perform(get("/category/1").header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON));
		createResult.andDo(print());
		createResult.andExpect(status().isOk());
	}
	
}
