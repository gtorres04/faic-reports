package com.gtorresoft.google.sheets.domain.service;

import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import java.util.List;

public interface GoogleSheetsService {
  List<List<Object>> get(GoogleSheetsDatasource googleSheetsDatasource);
}
