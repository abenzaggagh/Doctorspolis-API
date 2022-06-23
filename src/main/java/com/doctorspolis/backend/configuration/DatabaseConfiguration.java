package com.doctorspolis.backend.configuration;

import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EntityScan("com.doctorspolis.backend.model")
@EnableJpaRepositories("com.doctorspolis.backend.repository")
public class DatabaseConfiguration implements CommandLineRunner  {

    // private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseConfiguration(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
/*
    @Bean
    CommandLineRunner initDatabase(DoctorRepository repository) {
        return args -> {
            // repository.save(new Doctor("Nephrology nurse", null, "burglar"));
            // repository.save(new Doctor("Frodo Baggins", "thief"));
        };
    }
*/
    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(User.builder()
                .username("abenzaggagh")
                .password(this.passwordEncoder.encode("password"))
                .enabled(true)
                .build());
    }
}