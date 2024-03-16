package org.example.divintosrpingmvc;

import org.example.divintosrpingmvc.entities.Patient;
import org.example.divintosrpingmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return args -> {
            UserDetails yassine = jdbcUserDetailsManager.loadUserByUsername("yassine");
            if(yassine == null){
                jdbcUserDetailsManager.createUser(User.withUsername("yassine").password(passwordEncoder.encode("1234")).roles("USER").build());
            }
            UserDetails roro = jdbcUserDetailsManager.loadUserByUsername("roro");
            if(roro == null){
                jdbcUserDetailsManager.createUser(User.withUsername("roro").password(passwordEncoder.encode("1234")).roles("USER").build());
            }
            UserDetails admin = jdbcUserDetailsManager.loadUserByUsername("admin");
            if(admin == null){
                jdbcUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());
            }


        };
    }

}
