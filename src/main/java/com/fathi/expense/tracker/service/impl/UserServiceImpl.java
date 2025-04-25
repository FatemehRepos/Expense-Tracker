package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.request.AuthenticationRequest;
import com.fathi.expense.tracker.model.response.UserResponse;
import com.fathi.expense.tracker.repository.UserRepository;
import com.fathi.expense.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse save(AuthenticationRequest request) {
        return mapToUserResponse(userRepository.save(mapToUser(request)));
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .build();
    }

    private User mapToUser(AuthenticationRequest request) {
        return User.builder()
                .username(request.username())
                .password(new BCryptPasswordEncoder().encode(request.password()))
                .build();
    }

}
