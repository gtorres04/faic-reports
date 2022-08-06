package com.gtorresoft.faic.reports.application;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.SearcherReportsPort;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class SearcherReportsServiceImplTest {

    @InjectMocks
    SearcherReportsServiceImpl searcherQuotasService;

    @Mock
    SearcherReportsPort searcherReportsPort;

    @Test
    void findReports(@Random List<Report> reports) {
        // Given
        when(searcherReportsPort.findReports()).thenReturn(reports);
        // When
        val result = searcherQuotasService.findReports();
        // Then
        assertThat(result).isEqualTo(reports);
    }

    @Test
    void findReportBy(@Random Report report) {
        // Given
        val partnerId = report.getId();
        List<Report> reports = List.of(report);
        when(searcherReportsPort.findReports()).thenReturn(reports);
        // When
        val result = searcherQuotasService.findReportBy(partnerId);
        // Then
        assertThat(result).isEqualTo(Optional.of(report));
    }
}