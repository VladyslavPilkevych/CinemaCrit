package com.cinemacrit.platform.configs;

import com.cinemacrit.platform.services.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConfigurationTests {

    // Make sure SecurityConfig is imported correctly if used
    @Configuration
    @Import({DataSourceConfiguration.class, SecurityConfig.class})
    class TestConfig {

        // Mock Environment bean
        @Bean
        public Environment mockEnvironment() {
            Environment env = mock(Environment.class);
            when(env.getProperty("driverClassName")).thenReturn("org.sqlite.JDBC");
            when(env.getProperty("url")).thenReturn("jdbc:sqlite:test.db");
            when(env.getProperty("username")).thenReturn("test_user");
            when(env.getProperty("password")).thenReturn("test_password");
            return env;
        }

        // Mock the CustomUserDetailsService bean
        @Bean
        public CustomUserDetailsService mockUserDetailsService() {
            return mock(CustomUserDetailsService.class);
        }

        // Mock the PasswordEncoder bean
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        // Mock the DaoAuthenticationProvider bean
        @Bean
        public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setUserDetailsService(customUserDetailsService);
            provider.setPasswordEncoder(passwordEncoder);
            return provider;
        }

        // Mock the SecurityFilterChain bean
        @Bean
        public SecurityFilterChain securityFilterChain() {
            return mock(SecurityFilterChain.class);
        }
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("filterChain")
    private SecurityFilterChain securityFilterChain;

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

//    @Test
//    void testDataSourceConfiguration() {
//        assertThat(dataSource).isNotNull();
//        assertThat(dataSource).isInstanceOf(DriverManagerDataSource.class);
//
//        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource) dataSource;
//        assertThat(driverManagerDataSource.getUrl()).isEqualTo("jdbc:sqlite:CinemaCrit.db");
//        assertThat(driverManagerDataSource.toString()).contains("org.sqlite.JDBC");
//    }

    @Test
    void testPasswordEncoderBean() {
        assertThat(passwordEncoder).isNotNull();
        assertThat(passwordEncoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void testAuthenticationProvider() {
        assertThat(authenticationProvider).isNotNull();
    }

    @Test
    void testSecurityFilterChain() {
        assertThat(securityFilterChain).isNotNull();
    }
}
