package me.wonwoo.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 톰캣 포트 변경
 * 
 * @author wonwoo
 *
 */
@Configuration
public class CustomizationBean {

	/**
	 * 톰캣 포트
	 *
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer setEmbeddedServletContainerCustomizer() {
		return (container) -> container.setPort(8888);
	}
}
