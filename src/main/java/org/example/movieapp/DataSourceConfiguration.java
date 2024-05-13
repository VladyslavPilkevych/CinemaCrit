package org.example.movieapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Configuration class for setting up the data source.
 */
@Configuration
public class DataSourceConfiguration {
    @Autowired
    Environment env;

    /**
     * Configures the data source bean.
     *
     * @return the configured data source
     */
    @Bean
    public DataSource dataSource(){
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driverClassName")));
        dataSource.setUrl(Objects.requireNonNull(env.getProperty("url")));
        return dataSource;
    }
}
