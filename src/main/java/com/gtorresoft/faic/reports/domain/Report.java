package com.gtorresoft.faic.reports.domain;

import java.time.Month;
import java.util.Map;
import lombok.*;

@Builder
public record Report(
    String id, String fullName, Double previousCapital, Map<Month, Double> quotaByMonth) {

  public double getTotalAmount() {
    return Double.sum(
        this.previousCapital(), this.quotaByMonth().values().stream().mapToDouble(v -> v).sum());
  }
}
