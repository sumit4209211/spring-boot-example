package me.wonwoo.config;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestAttributes;

@Configuration
public class ConfigMvc {

	// @Autowired
	// private AccountsService accountsService;

	// @Bean
	// public EmbeddedServletContainerFactory servletContainer() {
	// TomcatEmbeddedServletContainerFactory factory = new
	// TomcatEmbeddedServletContainerFactory();
	// factory.setPort(8090);
	// factory.setSessionTimeout(10, TimeUnit.MINUTES);
	// return factory;
	// }

	// @Bean
	// public InitializingBean init() {
	// return () -> {
	// System.out.println(passwordEncoder().encode("pwadmin"));
	//
	// };
	// }

	@Bean
	public ErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes() {
			@Override
			public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
					boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
				// BasicErrorController
				// Customize the default entries in errorAttributes to suit your
				// needs
				return errorAttributes;
			}

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
