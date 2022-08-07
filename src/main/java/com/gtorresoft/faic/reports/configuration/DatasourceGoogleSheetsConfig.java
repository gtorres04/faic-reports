package com.gtorresoft.faic.reports.configuration;

import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceGoogleSheetsConfig {

  @Bean
  public GoogleSheetsDatasource googleSheetsDatasourceReports() {
    // TODO sacar como propiedades
    return GoogleSheetsDatasource.builder()
        .tabName("Cuotas G1")
        .spreadSheetsId("1Tki5iQhqveiBS8aN4IiT-z8RaqJMwEjONgan4fa8u68")
        .id("reports")
        .range("A5:Q")
        .build();
  }
}
