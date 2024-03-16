package org.example.divintosrpingmvc.security.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.divintosrpingmvc.security.entities.AppRole;
import org.example.divintosrpingmvc.security.entities.AppUser;
import org.example.divintosrpingmvc.security.repositories.AppRoleRepository;
import org.example.divintosrpingmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String email, String password, String confirmedPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser != null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("passwords do not match");
        appUser= AppUser.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser ;
    }

    @Override
    public AppRole addNewRole(String Role) {
        AppRole appRole = appRoleRepository.findById(Role).orElse(null);
        if(appRole != null) throw new RuntimeException("Role already exists");
        appRole= AppRole.builder()
                .role(Role)
                .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String Role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(Role).orElse(null);
        if(appRole == null) throw new RuntimeException("Role not found");
        if(appUser == null) throw new RuntimeException("User not found");
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleToUser(String username, String Role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(Role).orElse(null);
        if(appRole == null) throw new RuntimeException("Role not found");
        if(appUser == null) throw new RuntimeException("User not found");
        appUser.getRoles().remove(appRole);
    }
    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
