package com.gtorresoft.google.sheets.application;

import com.google.api.services.sheets.v4.Sheets;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import com.gtorresoft.google.sheets.domain.exception.GetValuesGivingSpreadSheetsIsOrRangeIncorrectException;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component // TODO borrar la anotacion
@AllArgsConstructor
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

  private final Sheets sheets;

  @Override
  public List<List<Object>> get(GoogleSheetsDatasource googleSheetsDatasource) {
    try {
      return sheets
          .spreadsheets()
          .values()
          .get(
              googleSheetsDatasource.spreadSheetsId(),
              String.format(
                  "%s!%s", googleSheetsDatasource.tabName(), googleSheetsDatasource.range()))
          .execute()
          .getValues();
    } catch (IOException e) {
      throw new GetValuesGivingSpreadSheetsIsOrRangeIncorrectException(e);
    }
  }
}
