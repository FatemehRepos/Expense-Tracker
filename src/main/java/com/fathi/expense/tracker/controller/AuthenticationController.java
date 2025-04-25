package com.fathi.expense.tracker.controller;

import com.fathi.expense.tracker.model.request.AuthenticationRequest;
import com.fathi.expense.tracker.model.response.AuthenticationResponse;
import com.fathi.expense.tracker.model.response.UserResponse;
import com.fathi.expense.tracker.security.JWTUtils;
import com.fathi.expense.tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final JWTUtils jwtUtils;
    private final UserService userService;
    private final UserDetailsService userDetailService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        initialAuthenticate(request.username(), request.password());
        UserDetails userDetails = userDetailService.loadUserByUsername(request.username());
        String token = jwtUtils.generateToken(userDetails);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expireAt(jwtUtils.extractExpirsionDate(token))
                .build();
        return ResponseEntity.ok(response);
    }

    private void initialAuthenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
