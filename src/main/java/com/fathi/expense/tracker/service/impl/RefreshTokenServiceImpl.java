package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.component.exception.InvalidTokenException;
import com.fathi.expense.tracker.component.exception.RecordNotFoundException;
import com.fathi.expense.tracker.model.entity.RefreshToken;
import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.repository.RefreshTokenRepository;
import com.fathi.expense.tracker.security.JwtConfig;
import com.fathi.expense.tracker.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtConfig config;
    private final RefreshTokenRepository repository;

    @Override
    public String issue(User user) {
        String rawToken = RefreshTokenUtils.generate();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(RefreshTokenUtils.hash(rawToken));
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(config.getRefreshTokenExpirationDay()));
        refreshToken.setUser(user);
        repository.save(refreshToken);
        return rawToken;
    }

    @Override
    public RefreshToken validate(String rawToken) {
        String hashToken = RefreshTokenUtils.hash(rawToken);
        RefreshToken refreshToken = findRefreshToken(hashToken);
        tokenIsRevoked(refreshToken);
        tokenIsExpired(refreshToken);
        return refreshToken;
    }

    @Override
    public void revoke(RefreshToken token) {
        token.setRevoked(true);
        repository.save(token);
    }

    @Override
    public void revokeAll(long userId) {
        repository.revokeAllByUserId(userId);
    }

    private RefreshToken findRefreshToken(String hashToken) {
        return repository.findByToken(hashToken)
                .orElseThrow(() -> new RecordNotFoundException("error.authenticated.user.not.found"));
    }

    private void tokenIsRevoked(RefreshToken refreshToken) {
        if (refreshToken.isRevoked())
            throw new InvalidTokenException("error.authenticated.user.not.found");
    }

    private void tokenIsExpired(RefreshToken refreshToken) {
        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new InvalidTokenException("error.authenticated.user.not.found");
    }

}
