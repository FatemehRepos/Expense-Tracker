package com.fathi.expense.tracker.service.impl;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.model.enums.RoleName;
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
    public User save(String username, String password) {
        User user = userRepository.save(mapToUser(username, password));
        userRoleService.save(user, RoleName.ROLE_USER.name());
        return user;
    }

    @Override
    public User getAuthenticatedUser() {
        return userRepository.findUserByUsername(getAuthenticatedUserInformation().getUsername())
                .orElseThrow(() -> new RuntimeException("error.authenticated.user.not.found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("error.authenticated.user.not.found"));
    }

//    @Override
//    public User getUserByEmail(String email) {
//        return userRepository.findUserByEmail(email)
//                .orElseThrow(() -> new RuntimeException("error.authenticated.user.not.found"));
//    }

    public UserDetails getAuthenticatedUserInformation() {
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        return principal instanceof UserDetails ? (UserDetails) principal : null;
    }

    private User mapToUser(String username, String password) {
        return User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .build();
    }

}
