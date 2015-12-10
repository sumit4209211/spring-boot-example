package me.wonwoo.config;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.wonwoo.account.Accounts;
import me.wonwoo.account.AccountsService;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;

@Configuration
public class ConfigMvc {

	@Autowired
	private AccountsService accountsService;

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

	@Bean
	public InitializingBean init() {
		return () -> {
			accountsService.saveAccounts(new Accounts("wonwoo999", "wonwon123"));
			accountsService.saveAccounts(new Accounts("wonwoo871", "8811jsnn"));
		};
	}

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
