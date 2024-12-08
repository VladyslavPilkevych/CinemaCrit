//package com.cinemacrit.platform.configs;
//
//import jakarta.activation.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//@Configuration
//public class DataSourceConfiguration {
//
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getProperty("driverClassName"));
//        dataSource.setUrl(environment.getProperty("url"));
//        dataSource.setUsername(environment.getProperty("username"));
//        dataSource.setPassword(environment.getProperty("password"));
//        return (DataSource) dataSource;
//    }
//}
