package com.gtorresoft.faic.reports.application;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.SearcherReportsPort;
import com.gtorresoft.faic.reports.domain.services.SearcherReportsService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component // TODO borrar la anotacion
@AllArgsConstructor
public class SearcherReportsServiceImpl implements SearcherReportsService {
  private final SearcherReportsPort searcherReportsPort;

  @Override
  public List<Report> findReports() {
    return searcherReportsPort.findReports();
  }

  @Override
  public Optional<Report> findReportBy(String partnerId) {
    return findReports().stream().filter(report -> partnerId.equals(report.id())).findFirst();
  }
}
