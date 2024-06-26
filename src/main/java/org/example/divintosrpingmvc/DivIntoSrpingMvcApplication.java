package org.example.divintosrpingmvc;

import org.example.divintosrpingmvc.entities.Patient;
import org.example.divintosrpingmvc.repositories.PatientRepository;
import org.example.divintosrpingmvc.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class DivIntoSrpingMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DivIntoSrpingMvcApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return args -> {
            try {
                jdbcUserDetailsManager.loadUserByUsername("yassine");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("yassine").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }
            try {
                jdbcUserDetailsManager.loadUserByUsername("roro");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("roro").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            }
            try {
                jdbcUserDetailsManager.loadUserByUsername("admin");
            } catch (UsernameNotFoundException e) {
                jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());
            }
        };
    }
    //@Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            Stream.of("romaisa","norama","yassine").forEach(name->{
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDateNaissance(new Date());
                patient.setScore(1200);
                patient.setMalade(Math.random()>0.5?true:false);
                patientRepository.save(patient);
            });
        };
    }

    //@Bean
    CommandLineRunner commandLineRunnerUserDetails (AccountService accountService) {
        return args -> {
            accountService.addNewUser("yassine", "yassine@gmail.com", "1234", "1234");
            accountService.addNewUser("roro", "roro@gmail.com", "1234", "1234");
            accountService.addNewUser("admin", "admin@gmail.com", "12345", "12345");

            accountService.addRoleToUser("yassine", "USER");
            accountService.addRoleToUser("roro", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
        };
    }

}
