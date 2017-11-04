package com.epam.cinema.configuration;

import com.epam.cinema.configuration.spring.AppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.nio.file.Paths;

public class WebServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private int maxUploadSizeInMb = 5 * 1024 * 1024;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet ds = new DispatcherServlet(servletAppContext);
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                Paths.get(System.getProperty("java.io.tmpdir")).toString(),
                maxUploadSizeInMb,
                maxUploadSizeInMb * 2,
                maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {  new DelegatingFilterProxy("springSecurityFilterChain") };
    }
}
