package com.crs.kttr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class KttrApplication {
  public static void main(String[] args) {
    SpringApplication.run(KttrApplication.class, args);
  }
}
