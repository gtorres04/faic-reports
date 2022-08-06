package com.gtorresoft.faic.reports.application;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.GeneratorReportsPort;
import com.gtorresoft.faic.reports.domain.services.GeneratorReportsService;
import com.gtorresoft.faic.reports.domain.services.SearcherReportsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service // TODO borrar la anotacion
@AllArgsConstructor
public class GeneratorReportsServiceImpl implements GeneratorReportsService {

    private final SearcherReportsService searcherReportsService;
    private final GeneratorReportsPort generateReports;

    @Override
    public void generateReportBy(String partnerId){
        Report report = searcherReportsService.findReportBy(partnerId)
                .orElseThrow(() -> new RuntimeException("partner no found")); // TODO LANZAR UN EXECTION CUSTOM
        generateReports.generatePdf(report);
    }

    @Override
    public void generateReports() {
        searcherReportsService.findReports().forEach(generateReports::generatePdf);
    }
}
