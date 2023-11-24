/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitittech.paygo.springservicegateway.controllers;

import com.github.javafaker.Faker;
import com.gitittech.paygo.auth.dtos.SecurityUser;
import java.util.Collection;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 *
 * @author ambag
 */
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@Testcontainers
public abstract class AbstractIntegrationTest {

    protected final static int MAILHOG_PORT_HTTP = 8085;
    protected final static int MAILHOG_PORT_SMTP = 2035;

    private final static MySQLContainer mysql;

    protected final Faker faker = new Faker();

    static {

        mysql = new MySQLContainer("mysql:8.0");
        mysql.start();
    }

    protected void login(String email, String password, Collection<GrantedAuthority> authorities) throws Exception {
        final var customUser = new SecurityUser(email, password, false, authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUser, customUser.getPassword(), customUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }
}
