package com.gtorresoft.faic.reports.infrastructure.controller.web;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.services.GeneratorReportsService;
import com.gtorresoft.faic.reports.domain.services.SearcherReportsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller
@AllArgsConstructor
@Slf4j
public class ReportsController {
  private final SearcherReportsService searcherReportsService;
  private final GeneratorReportsService generatorReportsService;

  @GetMapping({"/listar", "/"})
  public String listar(Model model) {
    Flux<Report> reportsFlux = Flux.fromIterable(searcherReportsService.findReports());
    model.addAttribute("reports", reportsFlux);
    model.addAttribute("titulo", "Listado de reportes");
    return "listar";
  }

  @GetMapping({"/generate-pdf"})
  public String generatePdf(Model model) {
    generatorReportsService.generateReports();
    return listar(model);
  }
}
