package com.gtorresoft.faic.reports.infrastructure.filesystem;

import com.gtorresoft.faic.reports.domain.Report;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import java.time.Month;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class GeneratorReportsAdapterTest {
  @InjectMocks GeneratorReportsAdapter generatorReportsAdapter;

  // TODO testear mejor esto
  @Test
  void generatePdf() {
    Report report =
        Report.builder()
            .fullName("test reports∫")
            .id("test")
            .previousCapital(1d)
            .quotaByMonth(Map.of(Month.APRIL, 1d))
            .build();
    generatorReportsAdapter.generatePdf(report);
  }
}
