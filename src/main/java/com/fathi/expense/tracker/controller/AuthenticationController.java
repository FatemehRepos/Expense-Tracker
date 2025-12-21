package com.fathi.expense.tracker.controller;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.request.AuthenticationRequest;
import com.fathi.expense.tracker.model.request.RefreshTokenRequest;
import com.fathi.expense.tracker.model.response.AuthenticationResponse;
import com.fathi.expense.tracker.model.response.Response;
import com.fathi.expense.tracker.model.response.UserCreationResponse;
import com.fathi.expense.tracker.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Response<UserCreationResponse>> register(@RequestBody @Valid AuthenticationRequest request) {
        User user = authenticationService.register(request.username(), request.password());
        UserCreationResponse response = UserCreationResponse.builder().id(user.getId()).build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Response.success(response));
    }

    @PostMapping("/login")
    public ResponseEntity<Response<AuthenticationResponse>> login(@RequestBody AuthenticationRequest request) {
        initialAuthenticate(request.username(), request.password());
        AuthenticationResponse response = authenticationService.login(request.username());
        return ResponseEntity.ok(Response.success(response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Response<AuthenticationResponse>> refresh(@RequestBody RefreshTokenRequest request) {
        AuthenticationResponse response = authenticationService.refresh(request.refreshToken());
        return ResponseEntity.ok(Response.success(response));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response<AuthenticationResponse>> logout(@RequestBody RefreshTokenRequest request) {
        authenticationService.logout(request.refreshToken());
        return ResponseEntity.ok(Response.success(null));
    }

    private void initialAuthenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
