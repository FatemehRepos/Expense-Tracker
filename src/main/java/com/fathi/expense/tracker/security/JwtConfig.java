package com.fathi.expense.tracker.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {

    private String secretKey = "";
    private int expirationMinutes = 0;
    private int refreshTokenExpirationDay = 1;

}
