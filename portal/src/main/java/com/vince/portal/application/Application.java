package com.vince.portal.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;

import com.vince.accessingdatajpa.CustomerService;
import com.vince.accessingdatajpa.CustomerServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication(scanBasePackages = {"com.vince.accessingdatajpa", "com.vince.portal.application"})
//@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
