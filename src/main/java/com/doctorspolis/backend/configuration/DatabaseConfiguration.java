package com.doctorspolis.backend.configuration;

import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.doctorspolis.backend.model")
@EnableJpaRepositories("com.doctorspolis.backend.repository")
public class DatabaseConfiguration {

    // private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DoctorRepository repository) {
        return args -> {
            // repository.save(new Doctor("Nephrology nurse", null, "burglar"));
            // repository.save(new Doctor("Frodo Baggins", "thief"));
        };
    }

}