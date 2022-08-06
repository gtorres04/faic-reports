package com.gtorresoft.google.sheets.application;

import com.gtorresoft.google.sheets.domain.GoogleSheetsDatasource;
import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
class GoogleSheetsServiceImplIntTest {

    @InjectMocks
    GoogleSheetsServiceImpl googleSheetsService;

    @Test
    void get() {
        GoogleSheetsDatasource googleSheetsDatasource = GoogleSheetsDatasource.builder()
                .spreadSheetsId("1U0j2xmOEGBlzX18VlsufbXH2RQgD1GAHiYprqSSeA30")
                .range("A2:D")
                .tabName("Hoja 1")
                .build();
        List<List<Object>> result = googleSheetsService.get(googleSheetsDatasource);
        assertThat(result).isNotEmpty();
        result.forEach(list -> assertThat(list).isNotEmpty().hasSize(4));
    }
}