package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.enums.RoleName;
import com.fathi.expense.tracker.model.request.AuthenticationRequest;
import com.fathi.expense.tracker.model.response.UserResponse;
import com.fathi.expense.tracker.repository.UserRepository;
import com.fathi.expense.tracker.service.UserRoleService;
import com.fathi.expense.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    @Override
    public UserResponse save(AuthenticationRequest request) {
        User user = userRepository.save(mapToUser(request));
        userRoleService.create(user, RoleName.ROLE_USER.name());
        return mapToUserResponse(user);
    }

    @Override
    public User getAuthenticatedUser() {
        return userRepository.findUserByUsername(getAuthenticatedUserInformation().getUsername())
                .orElseThrow(() -> new RuntimeException("error.authenticated.user.not.found"));
    }

    public UserDetails getAuthenticatedUserInformation() {
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        return principal instanceof UserDetails ? (UserDetails) principal : null;
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
