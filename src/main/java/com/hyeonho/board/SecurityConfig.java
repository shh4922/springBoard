package com.hyeonho.board;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/api/v1/user/login").permitAll()
                                .requestMatchers("/api/v1/user/register").permitAll()
                                .requestMatchers("/api/v1/board/create").permitAll()
                                .requestMatchers("/api/v1/board/posts").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }
    // 뭐임? 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
