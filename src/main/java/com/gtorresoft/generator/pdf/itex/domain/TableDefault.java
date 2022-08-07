package com.gtorresoft.generator.pdf.itex.domain;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import lombok.Builder;
import lombok.Getter;

public class TableDefault {
  @Getter private final com.itextpdf.layout.element.Table table;

  @Builder
  public TableDefault(int amountColumn) {
    this.table =
        new com.itextpdf.layout.element.Table(UnitValue.createPercentArray(amountColumn))
            .useAllAvailableWidth();
  }

  public TableDefault addCell(String text, int rowspan, int colspan, Color color) {
    Cell cell = new Cell(rowspan, colspan).add(new Paragraph(text));
    cell.setBackgroundColor(color);
    table.addCell(cell);
    return this;
  }

  public TableDefault addCell(String text, Color color) {
    Cell cell = new Cell().add(new Paragraph(text));
    cell.setBackgroundColor(color);
    table.addCell(cell);
    return this;
  }
}
