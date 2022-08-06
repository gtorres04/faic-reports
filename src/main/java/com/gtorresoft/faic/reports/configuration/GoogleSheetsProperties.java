package com.gtorresoft.faic.reports.configuration;

import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "google-sheets")
class GoogleSheetsProperties{
    private List<GoogleSheetsDatasource> dataSources;
}
