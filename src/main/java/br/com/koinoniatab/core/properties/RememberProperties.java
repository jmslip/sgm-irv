package br.com.koinoniatab.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "remember")
public class RememberProperties {
    private String key;
    private int tokenValiditySeconds;
}
