package com.study.boardflab.config;

import com.study.boardflab.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class AuthenticationProviderConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public AuthenticationProviderConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public AuthenticationProvider configureAuthenticationProvider(){
        return this.customAuthenticationProvider;
    }
}
