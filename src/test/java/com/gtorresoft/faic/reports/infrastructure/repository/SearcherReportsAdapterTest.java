package com.gtorresoft.faic.reports.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.SearchReportsException;
import com.gtorresoft.faic.reports.infrastructure.repository.mappers.SearcherReportsAdapterMapper;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class SearcherReportsAdapterTest {

  SearcherReportsAdapter searcherReportsAdapter;
  GoogleSheetsService googleSheetsService;
  SearcherReportsAdapterMapper searcherReportsAdapterMapper;
  GoogleSheetsDatasource googleSheetsDatasource = GoogleSheetsDatasource.builder().build();

  @BeforeEach
  void setUp() {
    googleSheetsService = mock(GoogleSheetsService.class);
    searcherReportsAdapterMapper = mock(SearcherReportsAdapterMapper.class);
    searcherReportsAdapter =
        new SearcherReportsAdapter(
            googleSheetsService, searcherReportsAdapterMapper, googleSheetsDatasource);
  }

  @Test
  void findReports() {
    Report report =
        Report.builder()
            .fullName("test reports∫")
            .id("test")
            .previousCapital(1d)
            .quotaByMonth(Map.of(Month.APRIL, 1d))
            .build();
    List<List<Object>> values = Collections.singletonList(Collections.EMPTY_LIST);
    when(googleSheetsService.get(googleSheetsDatasource)).thenReturn(values);
    when(searcherReportsAdapterMapper.listObjectsToReport(anyList())).thenReturn(report);

    val result = searcherReportsAdapter.findReports();

    assertThat(result).isNotEmpty();
    assertThat(result)
        .isEqualTo(
            IntStream.of(values.size()).mapToObj(operand -> report).collect(Collectors.toList()));
    verify(googleSheetsService, times(1)).get(googleSheetsDatasource);
    verify(searcherReportsAdapterMapper, times(values.size())).listObjectsToReport(anyList());
  }

  @Test
  void findReports_throwsException() {
    when(googleSheetsService.get(googleSheetsDatasource)).thenThrow(RuntimeException.class);

    assertThatThrownBy(() -> searcherReportsAdapter.findReports())
        .isInstanceOf(SearchReportsException.class)
        .hasMessage("Error al recuperar los datos desde la hoja de calculo de google");

    verify(googleSheetsService, times(1)).get(googleSheetsDatasource);
  }
}
