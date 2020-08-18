package com.oklimenko.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PassordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
//    public BCryptPasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();// new BCryptPasswordEncoder(4);
        return new BCryptPasswordEncoder(4);
    }
}
