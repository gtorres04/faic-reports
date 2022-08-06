package com.gtorresoft.faic.reports.infrastructure.repository;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.infrastructure.repository.mappers.SearcherReportsAdapterMapper;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import lombok.val;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class SearcherReportsAdapterTest {

    @InjectMocks
    SearcherReportsAdapter searcherReportsAdapter;
    @Mock
    GoogleSheetsService googleSheetsService;
    @Mock
    SearcherReportsAdapterMapper searcherReportsAdapterMapper;
    @Mock
    GoogleSheetsDatasource googleSheetsDatasource;

    @Test
    @Disabled("Mejorar el test unitario")
    void findReports(@Random List<List<Object>> values, @Random Report report) {
        when(googleSheetsService.get(googleSheetsDatasource)).thenReturn(values);
        when(searcherReportsAdapterMapper.listObjectsToReport(anyList())).thenReturn(report);

        val result = searcherReportsAdapter.findReports();

        assertThat(result).isEqualTo(IntStream.of(values.size()).mapToObj(operand -> report).collect(Collectors.toList()));
        verify(googleSheetsService, times(1)).get(googleSheetsDatasource);
        verify(searcherReportsAdapterMapper, times(values.size())).listObjectsToReport(anyList());
    }
}