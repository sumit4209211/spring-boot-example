package me.wonwoo.config;

import org.glassfish.jersey.server.ResourceConfig;

//@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(Endpoint.class);
	}
}