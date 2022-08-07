package com.gtorresoft.google.sheets.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import com.gtorresoft.google.sheets.domain.exception.GetValuesGivingSpreadSheetsIsOrRangeIncorrectException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class GoogleSheetsServiceImplIntTest {

  @InjectMocks GoogleSheetsServiceImpl googleSheetsService;

  @Mock Sheets sheets;

  @Mock Sheets.Spreadsheets spreadsheets;
  @Mock Sheets.Spreadsheets.Values values;
  @Mock Sheets.Spreadsheets.Values.Get get;

  @Captor ArgumentCaptor<String> argumentCaptorSpreadSheetsId;
  @Captor ArgumentCaptor<String> argumentCaptorRange;

  @Test
  void get() throws IOException {
    when(sheets.spreadsheets()).thenReturn(spreadsheets);
    when(spreadsheets.values()).thenReturn(values);
    when(values.get(argumentCaptorSpreadSheetsId.capture(), argumentCaptorRange.capture()))
        .thenReturn(get);
    List<List<Object>> listValues = Collections.singletonList(Collections.EMPTY_LIST);
    ValueRange valueRange = new ValueRange();
    valueRange.setValues(listValues);
    when(get.execute()).thenReturn(valueRange);
    GoogleSheetsDatasource googleSheetsDatasource =
        GoogleSheetsDatasource.builder()
            .spreadSheetsId("1U0j2xmOEGBlzX18VlsufbXH2RQgD1GAHiYprqSSeA30")
            .range("A2:D")
            .tabName("Hoja 1")
            .build();

    List<List<Object>> result = googleSheetsService.get(googleSheetsDatasource);

    assertThat(result).isNotEmpty();
    assertThat(result).isEqualTo(listValues);
    result.forEach(list -> assertThat(list).isEmpty());
    verify(values)
        .get(
            googleSheetsDatasource.spreadSheetsId(),
            String.format(
                "%s!%s", googleSheetsDatasource.tabName(), googleSheetsDatasource.range()));
    verifyNoMoreInteractions(sheets, spreadsheets, values, get);
  }

  @Test
  void get_throws_exception() throws IOException {
    when(sheets.spreadsheets()).thenReturn(spreadsheets);
    when(spreadsheets.values()).thenReturn(values);
    when(values.get(argumentCaptorSpreadSheetsId.capture(), argumentCaptorRange.capture()))
        .thenThrow(IOException.class);
    GoogleSheetsDatasource googleSheetsDatasource =
        GoogleSheetsDatasource.builder()
            .spreadSheetsId("1U0j2xmOEGBlzX18VlsufbXH2RQgD1GAHiYprqSSeA30")
            .range("A2:D")
            .tabName("Hoja 1")
            .build();

    assertThatThrownBy(() -> googleSheetsService.get(googleSheetsDatasource))
        .isInstanceOf(GetValuesGivingSpreadSheetsIsOrRangeIncorrectException.class);
    verifyNoMoreInteractions(sheets, spreadsheets, values, get);
  }
}
