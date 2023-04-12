package com.crs.kttr.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
@Getter
@Setter
public class DatabaseProperties {
  private String driverClassName;

  private String url;

  private String username;

  private String password;
}
