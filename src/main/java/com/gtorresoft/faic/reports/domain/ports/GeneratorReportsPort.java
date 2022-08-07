package com.gtorresoft.faic.reports.domain.ports;

import com.gtorresoft.faic.reports.domain.Report;

public interface GeneratorReportsPort {
  void generatePdf(Report report);
}
