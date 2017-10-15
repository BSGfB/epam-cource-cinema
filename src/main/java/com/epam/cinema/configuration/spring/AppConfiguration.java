package com.epam.cinema.configuration.spring;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
@ComponentScan("com.epam.cinema")
@PropertySource({"classpath:properties/shell.properties"})
@EnableAspectJAutoProxy
public class AppConfiguration {

    @Bean
    @Profile("database")
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurerDatabase() throws IOException {
        PropertySourcesPlaceholderConfigurer propertyPlaceholder = new PropertySourcesPlaceholderConfigurer();
        propertyPlaceholder.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath:queries/**/*.properties"));

        return propertyPlaceholder;
    }

    @Bean
    @Profile("static-data")
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
