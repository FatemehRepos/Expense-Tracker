package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.model.entity.RefreshToken;
import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.response.AuthenticationResponse;
import com.fathi.expense.tracker.security.JWTUtils;
import com.fathi.expense.tracker.service.AuthenticationService;
import com.fathi.expense.tracker.service.RefreshTokenService;
import com.fathi.expense.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JWTUtils jwtUtils;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public User register(String username, String password) {
        return userService.save(username, password);
    }

    @Override
    public AuthenticationResponse login(String username) {
        return generateToken(username);
    }

    @Override
    public AuthenticationResponse refresh(String refreshToken) {
        RefreshToken token = refreshTokenService.validate(refreshToken);
        if (token.isRevoked()) {
            refreshTokenService.revokeAll(token.getUser().getId());
            throw new SecurityException("error.authenticated.user.not.found");
        }
        refreshTokenService.revoke(token);
        refreshTokenService.issue(token.getUser());
        return generateToken(token.getUser().getUsername());
    }

    @Override
    public void logout(String refreshToken) {
        RefreshToken token = refreshTokenService.validate(refreshToken);
        refreshTokenService.revoke(token);
    }

    private AuthenticationResponse generateToken(String username) {
        String token = jwtUtils.generateToken(username);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshTokenService.issue(userService.getUserByUsername(username)))
                .tokenType("Bearer")
                .expireAt(jwtUtils.extractExpirsionDate(token))
                .build();
    }

}
