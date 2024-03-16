package org.example.divintosrpingmvc.security.repositories;

import jdk.jfr.Registered;
import org.example.divintosrpingmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, String> {
}
