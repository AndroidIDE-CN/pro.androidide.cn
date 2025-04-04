package com.api.aidepro.console;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter @Getter @Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private String allowedOrigins;
}