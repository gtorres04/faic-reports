package com.gtorresoft.faic.reports.infrastructure.controller.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gtorresoft.faic.reports.domain.services.GeneratorReportsService;
import com.gtorresoft.faic.reports.domain.services.SearcherReportsService;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class ReportsControllerTest {
  @InjectMocks ReportsController reportsController;

  @Mock SearcherReportsService searcherReportsService;
  @Mock GeneratorReportsService generatorReportsService;

  @Test
  void listar() {
    Model model = mock(Model.class);
    String result = reportsController.listar(model);
    assertNotNull(result);
    assertThat(result).isEqualTo("listar");
    verify(searcherReportsService).findReports();
    verifyNoMoreInteractions(searcherReportsService);
  }

  @Test
  void generatePdf() {
    Model model = mock(Model.class);
    String result = reportsController.generatePdf(model);
    assertNotNull(result);
    assertThat(result).isEqualTo("listar");
    verify(generatorReportsService).generateReports();
    verify(searcherReportsService).findReports();
    verifyNoMoreInteractions(generatorReportsService, searcherReportsService);
  }
}
