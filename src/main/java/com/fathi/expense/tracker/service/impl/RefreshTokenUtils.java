package com.fathi.expense.tracker.service.impl;


import org.springframework.security.core.token.Sha512DigestUtils;

import java.security.SecureRandom;
import java.util.Base64;

public class RefreshTokenUtils {

    public static String generate() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String hash(String token) {
        return Sha512DigestUtils.shaHex(token);
    }

}
