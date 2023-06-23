package com.crs.kttr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "database")
//@ConstructorBinding
public class DatabaseProperties {
  private String driverClassName;

  private String url;

  private String username;

  private String password;
}
