package org.example.divintosrpingmvc.security.service;

import lombok.AllArgsConstructor;
import org.example.divintosrpingmvc.security.entities.AppUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AccountService accountService;
    public UserDetailServiceImpl(@Lazy AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);
        if(appUser == null) throw new UsernameNotFoundException("User not found");
        String[] roles = appUser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);
        UserDetails userDetails = User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(roles)
                .build();
        return userDetails;
    }
}

