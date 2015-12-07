package me.wonwoo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wonwoo on 15. 12. 7..
 */
@Component
@ConfigurationProperties(prefix="my")
public class Config {
    private List<String> servers = new ArrayList<>();

    public List<String> getServers() {
        return this.servers;
    }
}
