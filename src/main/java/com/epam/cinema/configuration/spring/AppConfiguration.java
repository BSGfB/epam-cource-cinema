package com.epam.cinema.configuration.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.epam.cinema")
@PropertySource({"classpath:properties/app.properties"})
@EnableAspectJAutoProxy
public class AppConfiguration {

}
