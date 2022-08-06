package com.gtorresoft.faic.reports.infrastructure.repository;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.SearcherReportsPort;
import com.gtorresoft.faic.reports.infrastructure.repository.mappers.SearcherReportsAdapterMapper;
import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import com.gtorresoft.google.sheets.domain.service.GoogleSheetsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component // TODO borrar la anotacion
@AllArgsConstructor
public class SearcherReportsAdapter implements SearcherReportsPort {
    private final GoogleSheetsService googleSheetsService;
    private final SearcherReportsAdapterMapper searcherReportsAdapterMapper;
    private final GoogleSheetsDatasource googleSheetsDatasourceReports;

    @Override
    public List<Report> findReports() {
        return googleSheetsService.get(googleSheetsDatasourceReports)
                .stream()
                .filter(Objects::nonNull)
                .map(searcherReportsAdapterMapper::listObjectsToReport)
                .collect(Collectors.toList());
    }
}
