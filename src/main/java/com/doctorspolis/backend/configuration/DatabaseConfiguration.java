package com.doctorspolis.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.doctorspolis.backend.model")
@EnableJpaRepositories("com.doctorspolis.backend.model.repository")
public class DatabaseConfiguration implements CommandLineRunner  {

    @Autowired
    public DatabaseConfiguration() { }

    @Override
    public void run(String... args) { }

}