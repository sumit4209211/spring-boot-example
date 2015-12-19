package me.wonwoo.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * 톰캣 포트 변경
 * 
 * @author wonwoo
 *
 */
//@Component
public class CustomizationBean implements EmbeddedServletContainerCustomizer {

	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(9000);
	}

}
