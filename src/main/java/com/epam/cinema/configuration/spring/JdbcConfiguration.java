package com.epam.cinema.configuration.spring;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@Configuration
@Profile("database")
@PropertySource("classpath:properties/database.properties")
public class JdbcConfiguration {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    private DatabasePopulator databasePopulator() throws IOException {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new PathMatchingResourcePatternResolver().getResources("sql/**/*.sql"));

        return populator;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(final DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) throws IOException {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());

        return initializer;
    }

    @Bean(initMethod="start", destroyMethod="stop")
    public Server h2WebConsoleServer () throws SQLException {
        return Server.createWebServer("-web","-webAllowOthers","-webDaemon","-webPort", "8082");
    }
}
