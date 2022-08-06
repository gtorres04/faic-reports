package com.gtorresoft.google.sheets.application;

import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import com.gtorresoft.google.sheets.infrastructure.GoogleSheetsUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Component // TODO borrar la anotacion
@AllArgsConstructor
public class GoogleSheetsServiceImpl implements GoogleSheetsService {
    @Override
    public List<List<Object>> get(GoogleSheetsDatasource googleSheetsDatasource) {
        try {
            return GoogleSheetsUtil.getSheetsService()
                    .spreadsheets()
                    .values()
                    .get(googleSheetsDatasource.spreadSheetsId(), String.format("%s!%s", googleSheetsDatasource.tabName(), googleSheetsDatasource.range()))
                    .execute()
                    .getValues();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
