package com.gtorresoft.faic.reports.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class ReportsConfigTest {
  @InjectMocks ReportsConfig reportsConfig;

  @Test
  void getGoogleSheetsService() {
    assertThat(reportsConfig.getGoogleSheetsService()).isNotNull();
  }
}
