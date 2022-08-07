package com.gtorresoft.faic.reports.configuration;

import com.gtorresoft.google.sheets.application.GoogleSheetsServiceImpl;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportsConfig {
  @Bean
  public GoogleSheetsService getGoogleSheetsService() {
    return new GoogleSheetsServiceImpl();
  }
}
