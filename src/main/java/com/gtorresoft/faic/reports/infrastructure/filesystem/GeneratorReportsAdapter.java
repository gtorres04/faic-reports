package com.gtorresoft.faic.reports.infrastructure.filesystem;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.GeneratorReportsPort;
import com.gtorresoft.generator.pdf.itex.domain.TableDefault;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.File;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component // TODO borrar anotacion
@AllArgsConstructor
public class GeneratorReportsAdapter implements GeneratorReportsPort {
  // TODO sacar a una propiedad
  public static final String FORMAT = "./target/sandbox/tables/%s_%s_.pdf";

  @Override
  public void generatePdf(Report report) {
    String dest = String.format(FORMAT, report.id(), LocalDateTime.now());
    File file = new File(dest);
    boolean mkdirs = file.getParentFile().mkdirs();
    try {
      generatePdf(report, dest);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void generatePdf(Report report, String dest) throws Exception {
    PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
    Document doc = new Document(pdfDoc);

    TableDefault tableDefault =
        TableDefault.builder()
            .amountColumn(2)
            .build()
            .addCell(report.fullName(), 1, 2, ColorConstants.WHITE)
            .addCell("TOTAL", 1, 2, ColorConstants.WHITE)
            .addCell(String.valueOf(report.getTotalAmount()), 1, 2, ColorConstants.WHITE)
            .addCell("CAPITAL INICIAL", 1, 1, ColorConstants.WHITE)
            .addCell(String.valueOf(report.previousCapital()), 1, 1, ColorConstants.WHITE)
            .addCell("MES", 1, 1, ColorConstants.WHITE)
            .addCell("AHORRO", 1, 1, ColorConstants.WHITE);

    report.quotaByMonth().keySet().stream()
        .sorted()
        .forEach(
            month ->
                tableDefault
                    .addCell(month.name(), ColorConstants.WHITE)
                    .addCell(report.quotaByMonth().get(month).toString(), ColorConstants.WHITE));
    doc.add(tableDefault.getTable());
    doc.close();
  }
}
