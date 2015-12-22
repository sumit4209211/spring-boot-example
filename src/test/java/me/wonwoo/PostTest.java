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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import me.wonwoo.blog.category.CategoryRepository;
import me.wonwoo.blog.category.CategoryService;
import me.wonwoo.blog.post.Post;
import me.wonwoo.blog.post.PostRepository;
import me.wonwoo.config.oauth2.AccessToken;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class PostTest {

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
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Autowired
	private PostRepository postRepository; 

	@Test
	public void createTest() throws JsonProcessingException, Exception {
		Category category = new Category();
		category.setName("카테고리");
		Category category2 = categoryService.save(category);
		Post post = new Post();
		post.setEmail("aoruqjfu@gmail.com");
		post.setTitle("테스터");
		post.setContent("내용");
		
		category.setCategoryId(category2.getCategoryId());
		post.setCategory(category);
		postRepository.save(post);
		
		Post getPost = postRepository.findOne(1L);
		System.out.println(getPost.getCategory());
		
		Post post1 = new Post();
		post1.setEmail("aoruqjfu@gmail.com");
		post1.setTitle("테스터123");
		post1.setContent("내용");
		post1.setCategory(category);
		postRepository.save(post1);
		
		Pageable pageable = new PageRequest(0, 10);
		
		Category category3 = new Category();
		category3.setCategoryId(1L);
		Page<Post> posts = postRepository.findByCategory(category3, pageable);
		System.out.println(posts.getContent());
	}
	
	@Test
	public void getPostTest() throws JsonProcessingException, Exception{
		ResultActions createResult = mockMvc.perform(get("/post/1").header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON));
		createResult.andDo(print());
		createResult.andExpect(status().isOk());
	}
	

}
