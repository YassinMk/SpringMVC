package org.example.divintosrpingmvc;

import org.example.divintosrpingmvc.entities.Patient;
import org.example.divintosrpingmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class DivIntoSrpingMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DivIntoSrpingMvcApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {

        };
    }

}
