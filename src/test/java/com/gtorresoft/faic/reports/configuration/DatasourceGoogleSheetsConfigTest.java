package com.gtorresoft.faic.reports.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class DatasourceGoogleSheetsConfigTest {
  @InjectMocks DatasourceGoogleSheetsConfig datasourceGoogleSheetsConfig;

  @Test
  void googleSheetsDatasourceReports() {
    GoogleSheetsDatasource result = datasourceGoogleSheetsConfig.googleSheetsDatasourceReports();
    assertThat(result).isNotNull();
    assertThat(result)
        .isEqualTo(
            GoogleSheetsDatasource.builder()
                .tabName("Cuotas G1")
                .spreadSheetsId("1Tki5iQhqveiBS8aN4IiT-z8RaqJMwEjONgan4fa8u68")
                .id("reports")
                .range("A5:Q")
                .build());
  }
}
