package com.epam.cinema.configuration;

import com.epam.cinema.configuration.spring.AppConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@Import({AppConfiguration.class})
@ActiveProfiles("database")
public class SpringTestConfiguration {
}
