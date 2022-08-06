package com.gtorresoft.faic.reports.infrastructure.filesystem;

import com.gtorresoft.faic.reports.domain.Report;
import com.gtorresoft.faic.reports.domain.ports.GeneratorReportsPort;
import com.gtorresoft.generator.pdf.itex.domain.TableDefault;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;

@Component // TODO borrar anotacion
@AllArgsConstructor
public class GeneratorReportsAdapter implements GeneratorReportsPort {
   // TODO sacar a una propiedad
    public static final String FORMAT = "./target/sandbox/tables/%s_%s_.pdf";
    @Override
    public void generatePdf(Report report) {
        String dest = String.format(FORMAT, report.getId(), LocalDateTime.now());
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

        TableDefault.TableDefaultBuilder tableDefaultBuilder = TableDefault.builder()
                .amountColumn(2)
                .addCell(report.getFullName(),1,2,ColorConstants.WHITE)
                .addCell("TOTAL",1,2,ColorConstants.WHITE)
                .addCell(String.valueOf(report.getTotalAmount()),1,2,ColorConstants.WHITE)
                .addCell("CAPITAL INICIAL",1,1,ColorConstants.WHITE)
                .addCell(String.valueOf(report.getPreviousCapital()),1,1,ColorConstants.WHITE)
                .addCell("MES",1,1,ColorConstants.WHITE)
                .addCell("AHORRO",1,1,ColorConstants.WHITE);

        report.getQuotaByMonth()
                .keySet()
                .stream().sorted()
                .forEach(month -> {
                    tableDefaultBuilder.addCell(month.name(), ColorConstants.WHITE);
                    tableDefaultBuilder.addCell(report.getQuotaByMonth().get(month).toString(), ColorConstants.WHITE);
                });
        doc.add(tableDefaultBuilder.build().getTable());
        doc.close();
    }
}
