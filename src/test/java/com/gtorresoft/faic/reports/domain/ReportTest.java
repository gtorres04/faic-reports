package com.gtorresoft.faic.reports.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ReportTest {

  @Test
  void getTotalAmount() {
    Report report =
        Report.builder().previousCapital(1d).quotaByMonth(Map.of(Month.APRIL, 1d)).build();
    double result = report.getTotalAmount();
    assertThat(result)
        .isEqualTo(
            Double.sum(
                report.previousCapital(),
                report.quotaByMonth().values().stream().mapToDouble(v -> v).sum()));
  }
}
