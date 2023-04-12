package com.crs.kttr.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EntityScan(basePackages = "com.crs.kttr")
@EnableJpaRepositories(basePackages = "com.crs.kttr")
public class DatabaseConfig {
  final DatabaseProperties databaseProperties;

  public DatabaseConfig(DatabaseProperties databaseProperties) {
    this.databaseProperties = databaseProperties;
  }

  @Primary
  @Bean(name = "jpaDataSource")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
      .url(databaseProperties.getUrl())
      .username(databaseProperties.getUsername())
      .password(databaseProperties.getPassword())
      .driverClassName(databaseProperties.getDriverClassName())
      .build();
  }
}
