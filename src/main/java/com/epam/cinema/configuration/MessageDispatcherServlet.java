package com.epam.cinema.configuration;

import com.epam.cinema.configuration.spring.AppConfiguration;
import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class MessageDispatcherServlet extends AbstractAnnotationConfigMessageDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/ws/*"};
    }
}
