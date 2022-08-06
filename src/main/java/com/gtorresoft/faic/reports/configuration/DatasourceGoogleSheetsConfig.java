package com.gtorresoft.faic.reports.configuration;

import com.gtorresoft.google.sheets.application.GoogleSheetsServiceImpl;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@AllArgsConstructor
public class DatasourceGoogleSheetsConfig {

    // private final GoogleSheetsProperties googleSheetsProperties;
    //@Value("${foo}")
    //String tabName;

    @Bean
    public GoogleSheetsDatasource googleSheetsDatasourceReports(){
        // TODO sacar como propiedades
        return GoogleSheetsDatasource.builder()
                .tabName("Cuotas G1")
                .spreadSheetsId("1Tki5iQhqveiBS8aN4IiT-z8RaqJMwEjONgan4fa8u68")
                .id("reports")
                .range("A5:Q")
                .build();
        /*return googleSheetsProperties.getDataSources()
                .stream()
                .filter(googleSheetsDatasource -> "reports".equals(googleSheetsDatasource.id()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No esta configurado la hoja de calculo de google en el properties"));*/
    }
}
