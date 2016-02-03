package me.wonwoo.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CustomizationBean {

	/**
	 * 톰캣 포트
	 *
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerCustomizer setEmbeddedServletContainerCustomizer() {
		return (container) -> container.setPort(8081);
	}

	@Bean
	public HealthIndicator healthIndicator(){
		return ()-> Health.status("I <3 SFJUG!").build();
	}
}
