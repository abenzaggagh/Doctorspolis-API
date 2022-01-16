package com.doctorspolis.backend.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.doctorspolis.backend.model")
@EnableJpaRepositories("com.doctorspolis.backend.repository")
public class DatabaseConfiguration { }