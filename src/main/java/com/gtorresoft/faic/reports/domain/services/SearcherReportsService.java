package com.gtorresoft.faic.reports.domain.services;

import com.gtorresoft.faic.reports.domain.Report;
import java.util.List;
import java.util.Optional;

public interface SearcherReportsService {
  List<Report> findReports();

  Optional<Report> findReportBy(String partnerId);
}
