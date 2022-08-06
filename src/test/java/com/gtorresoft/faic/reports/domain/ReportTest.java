package com.gtorresoft.faic.reports.domain;

import io.github.glytching.junit.extension.random.Random;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void getTotalAmount(@Random Report report) {
        double result = report.getTotalAmount();
        Assertions.assertThat(result).isEqualTo(Double.sum(report.getPreviousCapital(), report.getQuotaByMonth().values().stream().mapToDouble(v -> v).sum()));
    }
}