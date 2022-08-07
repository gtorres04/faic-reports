package com.gtorresoft.faic.reports.infrastructure.repository.mappers;

import com.gtorresoft.faic.reports.domain.Report;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearcherReportsAdapterMapper {
  default Report listObjectsToReport(List<Object> list) {
    return Report.builder()
        .id(list.get(0).toString())
        .fullName(list.get(1).toString())
        .previousCapital(getDouble(list.get(4)))
        .quotaByMonth(getQuotaByMonth(list))
        .build();
  }

  private Double getDouble(Object o) {
    return Double.parseDouble(getValue(o));
  }

  private String getValue(Object o) {
    return o.toString().trim().replace(".", "").replace(",", ".").substring(1);
  }

  private Map<Month, Double> getQuotaByMonth(List<Object> list) {
    Map<Month, Double> monthDoubleMap = new HashMap<>();
    IntStream.range(5, 17)
        .sequential()
        .forEach(
            value -> monthDoubleMap.put(Month.values()[value - 5], getDouble(list.get(value))));
    return monthDoubleMap;
  }
}
