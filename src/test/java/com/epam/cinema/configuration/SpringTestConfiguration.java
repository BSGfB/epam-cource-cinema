package com.epam.cinema.configuration;

import com.epam.cinema.configuration.spring.AppConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfiguration.class})
public class SpringTestConfiguration {
}
