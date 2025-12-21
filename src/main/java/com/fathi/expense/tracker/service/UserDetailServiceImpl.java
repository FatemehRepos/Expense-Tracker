package com.fathi.expense.tracker.service;

import com.fathi.expense.tracker.model.entity.User;
import com.fathi.expense.tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails
    loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("error.user.not.found"));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getUserRole().forEach(userRole -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
            userRole.getRole().getRolePermissions().forEach(rolePermission ->
                    grantedAuthorities.add(new SimpleGrantedAuthority(rolePermission.getPermission().getName())));
        });
        return mapToUserDetails(user, grantedAuthorities);
    }

    private UserDetails mapToUserDetails(User user, List<GrantedAuthority> grantedAuthorities) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }

}
