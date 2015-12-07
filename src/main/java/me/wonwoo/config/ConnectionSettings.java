package me.wonwoo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * Created by wonwoo on 15. 12. 7..
 */
@Component
@ConfigurationProperties(prefix="connection")
@Data
public class ConnectionSettings {

    private String username;

    private InetAddress remoteAddress;

}