package com.crs.kttr.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Configuration.
 *
 * @author Heeyeon.
 */
@Configuration
public class SecurityConfig {
  /**
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeRequests((authz) -> authz
        .requestMatchers("/health/**").permitAll()
        .anyRequest()
        .authenticated()
    ).build();
  }
}
