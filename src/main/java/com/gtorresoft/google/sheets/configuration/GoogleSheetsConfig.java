package com.gtorresoft.google.sheets.configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleSheetsConfig {
  private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";

  private static final JsonFactory JSON_FACTORY =
      GsonFactory.getDefaultInstance(); // TODO Sacar a una propiedad
  private static final String TOKENS_DIRECTORY_PATH = "tokens"; // TODO Sacar a una propiedad
  private static final String CREDENTIALS_FILE_PATH =
      "/credentials.json"; // TODO Sacar a una propiedad
  /**
   * Global instance of the scopes required by this quickstart. If modifying these scopes, delete
   * your previously saved tokens/ folder.
   */
  // private static final List<String> SCOPES =
  // Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
  private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);

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

  @Bean
  public InputStream inputStream() {
    return GoogleSheetsConfig.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
  }

  @Bean
  public NetHttpTransport netHttpTransport() throws GeneralSecurityException, IOException {
    return GoogleNetHttpTransport.newTrustedTransport();
  }

  @Bean
  public GoogleClientSecrets googleClientSecrets(InputStream inputStream) throws IOException {
    return GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStream));
  }

  @Bean
  public GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow(
      GoogleClientSecrets clientSecrets, NetHttpTransport netHttpTransport) throws IOException {
    return new GoogleAuthorizationCodeFlow.Builder(
            netHttpTransport, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
  }

  @Bean
  public Credential authorize(GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow)
      throws IOException {
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    return new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, receiver)
        .authorize("user");
  }

  @Bean
  public Sheets sheets(Credential credential, NetHttpTransport netHttpTransport) {
    return new Sheets.Builder(netHttpTransport, GsonFactory.getDefaultInstance(), credential)
        .setApplicationName(APPLICATION_NAME)
        .build();
  }
}
