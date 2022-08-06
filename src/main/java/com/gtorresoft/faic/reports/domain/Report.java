package com.gtorresoft.faic.reports.domain;

import lombok.*;

import java.time.Month;
import java.util.Map;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Report {
    private String id;
    private String fullName;
    private Double previousCapital;
    private Map<Month, Double> quotaByMonth;

    public double getTotalAmount() {
        return Double.sum(this.getPreviousCapital(), this.getQuotaByMonth().values().stream().mapToDouble(v -> v).sum());
    }
}
