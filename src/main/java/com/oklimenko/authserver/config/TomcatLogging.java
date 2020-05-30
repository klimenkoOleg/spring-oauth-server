package com.oklimenko.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@Configuration
public class TomcatLogging {

    @Bean(name = "TeeFilter")
    public Filter teeFilter() {
        return new ch.qos.logback.access.servlet.TeeFilter();
    }
}
