package com.gtorresoft.google.sheets.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class GoogleSheetsConfigTest {

  @InjectMocks GoogleSheetsConfig googleSheetsConfig;

  @Test
  void netHttpTransport() throws GeneralSecurityException, IOException {
    assertThat(googleSheetsConfig.netHttpTransport()).isNotNull();
  }

  @Test
  void inputStream() {
    assertThat(googleSheetsConfig.inputStream()).isNotNull();
  }

  @Test
  void googleClientSecrets() throws IOException {
    assertThat(googleSheetsConfig.googleClientSecrets(googleSheetsConfig.inputStream()))
        .isNotNull();
  }

  @Test
  void googleAuthorizationCodeFlow() throws IOException, GeneralSecurityException {
    assertThat(
            googleSheetsConfig.googleAuthorizationCodeFlow(
                googleSheetsConfig.googleClientSecrets(googleSheetsConfig.inputStream()),
                googleSheetsConfig.netHttpTransport()))
        .isNotNull();
  }

  @Test
  void authorize() throws IOException, GeneralSecurityException {
    assertThat(
            googleSheetsConfig.authorize(
                googleSheetsConfig.googleAuthorizationCodeFlow(
                    googleSheetsConfig.googleClientSecrets(googleSheetsConfig.inputStream()),
                    googleSheetsConfig.netHttpTransport())))
        .isNotNull();
  }

  @Test
  void sheets(@Mock Credential credential) throws GeneralSecurityException, IOException {
    NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
    assertThat(googleSheetsConfig.sheets(credential, netHttpTransport)).isNotNull();
  }

  @Test
  void googleSheetsDatasourceReports() {
    GoogleSheetsDatasource result = googleSheetsConfig.googleSheetsDatasourceReports();
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
