package com.gtorresoft.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gtorresoft.*")
public class ReportsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReportsApplication.class, args);
  }
}
