package com.gtorresoft.google.sheets.domain;

import lombok.Builder;

@Builder
public record GoogleSheetsDatasource(String id, String spreadSheetsId, String tabName, String range) {
}
