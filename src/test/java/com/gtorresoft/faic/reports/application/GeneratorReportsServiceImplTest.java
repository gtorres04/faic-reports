package com.gtorresoft.faic.reports.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.GeneratorReportsPort;
import com.gtorresoft.faic.reports.domain.services.SearcherReportsService;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class GeneratorReportsServiceImplTest {

  @InjectMocks GeneratorReportsServiceImpl generatorReportsService;

  @Mock SearcherReportsService searcherReportsService;
  @Mock GeneratorReportsPort generateReports;

  @Test
  void generateReportBy(@Random String partnerId) {
    Report report = Report.builder().build();
    Optional<Report> reportOptional = Optional.of(report);
    when(searcherReportsService.findReportBy(partnerId)).thenReturn(reportOptional);

    generatorReportsService.generateReportBy(partnerId);

    verify(searcherReportsService).findReportBy(partnerId);
    verify(generateReports).generatePdf(reportOptional.get());
    verifyNoMoreInteractions(searcherReportsService, generateReports);
  }

  @Test
  void generateReportBy_then_throw_exception_when_report_is_not_found(@Random String partnerId) {
    when(searcherReportsService.findReportBy(partnerId)).thenReturn(Optional.empty());

    RuntimeException result =
        assertThrows(
            RuntimeException.class, () -> generatorReportsService.generateReportBy(partnerId));

    assertThat(result.getMessage()).isEqualTo("partner no found");
    verify(searcherReportsService).findReportBy(partnerId);
    verifyNoMoreInteractions(searcherReportsService);
  }

  @Test
  void generateReports() {
    Report report = Report.builder().build();
    List<Report> reports = Collections.singletonList(report);
    when(searcherReportsService.findReports()).thenReturn(reports);
    generatorReportsService.generateReports();
    verify(searcherReportsService).findReports();
    verify(generateReports).generatePdf(reports.get(0));
    verifyNoMoreInteractions(searcherReportsService, generateReports);
  }
}
