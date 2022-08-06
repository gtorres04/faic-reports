package com.gtorresoft.faic.reports.infrastructure.repository.mappers;

import io.github.glytching.junit.extension.random.Random;
import io.github.glytching.junit.extension.random.RandomBeansExtension;
import lombok.val;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class, RandomBeansExtension.class})
class SearcherReportsAdapterMapperImplTest {

    @InjectMocks
    SearcherReportsAdapterMapperImpl searcherReportsAdapterMapper;

    @ParameterizedTest
    @CsvSource({"'',ABC,'$2.502.134,50','',ABC,2502134.50", "'',ABC,$2.502.134,'',ABC,2502134"})
    void listObjectsToReport(String idTest, String fullNameTest, String previousCapitalTest, String id, String fullName, Double previousCapital) {
        List<Object> list = new EasyRandom().objects(Object.class, 17).collect(Collectors.toList());
        list.add(0, idTest);
        list.add(1, fullNameTest);
        list.add(4, previousCapitalTest);
        IntStream.range(5, 17).sequential().forEach(value -> list.add(value, String.format("%s%s", "$", value * 10000)));
        val result = searcherReportsAdapterMapper.listObjectsToReport(list);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getFullName()).isEqualTo(fullName);
        assertThat(result.getPreviousCapital()).isEqualTo(previousCapital);
        Arrays.asList(Month.values()).forEach(month -> assertThat(result.getQuotaByMonth().get(month)).isEqualTo(Double.valueOf((month.ordinal() + 5) * 10000)));
    }
}