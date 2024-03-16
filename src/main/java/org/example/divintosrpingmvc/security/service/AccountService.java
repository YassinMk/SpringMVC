package org.example.divintosrpingmvc.security.service;

import org.example.divintosrpingmvc.security.entities.AppRole;
import org.example.divintosrpingmvc.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String email, String password, String confirmedPassword);
    AppRole addNewRole(String Role);
    void addRoleToUser(String username, String Role);
    void removeRoleToUser(String username, String Role);
    AppUser loadUserByUsername(String username);
}
