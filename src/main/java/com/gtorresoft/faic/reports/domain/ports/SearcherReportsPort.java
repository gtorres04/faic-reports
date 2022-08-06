package com.gtorresoft.faic.reports.domain.ports;

import com.gtorresoft.faic.reports.domain.Report;

import java.util.List;

public interface SearcherReportsPort {
    List<Report> findReports();
}
