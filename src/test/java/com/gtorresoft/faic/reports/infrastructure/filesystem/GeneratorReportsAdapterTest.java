package com.gtorresoft.faic.reports.infrastructure.filesystem;

import com.gtorresoft.faic.reports.domain.Report;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class GeneratorReportsAdapterTest {
    @InjectMocks
    GeneratorReportsAdapter generatorReportsAdapter;

    // TODO testear mejor esto
    @Test
    void generatePdf(@Random Report report){
        generatorReportsAdapter.generatePdf(report);
    }

}