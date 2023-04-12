package com.crs.kttr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String[] WHITE_LIST = {
    "/health/**",
    "/api/v1/guest/**"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();

    return http
      .securityMatcher("/api/**")
      .authorizeHttpRequests((authz) -> authz
      .requestMatchers(WHITE_LIST).permitAll()
      .anyRequest().authenticated()
    )
    .build();
  }
}
