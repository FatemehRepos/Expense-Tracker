package com.fathi.expense.tracker.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {

    private String secretKey = "";
    private long expirationTime = 0L;

}
