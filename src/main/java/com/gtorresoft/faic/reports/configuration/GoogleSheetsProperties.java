package com.gtorresoft.faic.reports.configuration;

import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "google-sheets")
class GoogleSheetsProperties {
  private List<GoogleSheetsDatasource> dataSources;
}
