package com.epam.cinema.configuration.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.io.IOException;

@Configuration
@Profile("spring-shell")
@PropertySource({"classpath:properties/shell.properties"})
public class ShellConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
