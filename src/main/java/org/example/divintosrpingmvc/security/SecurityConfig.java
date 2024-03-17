package org.example.divintosrpingmvc.security;


import lombok.AllArgsConstructor;
import org.example.divintosrpingmvc.security.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private UserDetailServiceImpl userDetailsServiceImpl;
    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    /*@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
            User.withUsername("yassine").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("roro").password(passwordEncoder().encode("1234")).roles("USER").build(),
                    User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").build()
        );
    }
    */

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin(form->form.loginPage("/login").permitAll())
                .authorizeHttpRequests(authrize->{
                  authrize.requestMatchers("/webjars/**").permitAll();
                  authrize.requestMatchers("/user/**").hasAnyAuthority("USER");
                  authrize.requestMatchers("/admin/**").hasAnyAuthority("ADMIN");
                  authrize.anyRequest().authenticated();
                })
                .exceptionHandling(exceptionHandlingConfigurer -> {
                    exceptionHandlingConfigurer.accessDeniedPage("/notAuthorized");
                })
                .userDetailsService(userDetailsServiceImpl)
                .rememberMe(rememberMe->rememberMe.key("rememberme").tokenValiditySeconds(120));

        return httpSecurity.build();
    }

}
