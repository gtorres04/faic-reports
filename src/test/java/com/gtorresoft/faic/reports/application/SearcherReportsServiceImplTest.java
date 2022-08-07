package com.gtorresoft.faic.reports.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.SearcherReportsPort;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import java.util.List;
import java.util.Optional;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class SearcherReportsServiceImplTest {

  @InjectMocks SearcherReportsServiceImpl searcherQuotasService;

  @Mock SearcherReportsPort searcherReportsPort;

  @Test
  void findReports() {
    // Given
    Report report = Report.builder().build();
    List<Report> reports = List.of(report);
    when(searcherReportsPort.findReports()).thenReturn(reports);
    // When
    val result = searcherQuotasService.findReports();
    // Then
    assertThat(result).isEqualTo(reports);
  }

  @Test
  void findReportBy(@Random String reportId) {
    // Given
    Report report = Report.builder().id(reportId).build();
    val partnerId = report.id();
    List<Report> reports = List.of(report);
    when(searcherReportsPort.findReports()).thenReturn(reports);
    // When
    val result = searcherQuotasService.findReportBy(partnerId);
    // Then
    assertThat(result).isEqualTo(Optional.of(report));
  }

  @Test
  void findReportBy_when_partner_id_is_not_found(
      @Random String reportId, @Random String partnerId) {
    // Given
    Report report = Report.builder().id(reportId).build();
    List<Report> reports = List.of(report);
    when(searcherReportsPort.findReports()).thenReturn(reports);
    // When
    val result = searcherQuotasService.findReportBy(partnerId);
    // Then
    assertThat(result).isEqualTo(Optional.empty());
  }
}
