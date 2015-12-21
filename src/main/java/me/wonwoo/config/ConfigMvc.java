package me.wonwoo.config;

import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigMvc {

//	 @Autowired
//	 private AccountsService accountsService;

	/**
	 * 톰캣 포트
	 * 
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		// factory.setPort(8090);
		factory.setSessionTimeout(10, TimeUnit.MINUTES);
		// factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
		// "/notfound.html"));
		return factory;
	}

//	@Bean
//	public InitializingBean init() {
//		return () -> {
//			accountsService.saveAccounts(new Accounts(1L, "aoruqjfu@gmail.com", "pwadmin","wonwoo" ,"lee","001", new Date(), new Date()));
//		};
//	}

	/**
	 * 패스워드 인코딩
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * 모델맵퍼
	 * 
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
