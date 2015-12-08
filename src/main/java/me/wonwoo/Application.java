package me.wonwoo;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	/**
	 * 톰캣 포트 변경
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

	// public InitializingBean init() {
	// return () -> {
	//
	// };
	// }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) throws InterruptedException {
//		Function<String, Integer> a = b -> Integer.parseInt(b);

		SpringApplication.run(Application.class, args);
	}
}
