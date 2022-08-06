package com.gtorresoft.generator.pdf.itex.domain;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;


public class TableDefault {
    @Getter
    private final com.itextpdf.layout.element.Table table;

    public TableDefault(int amountColumn) {
        this.table = new com.itextpdf.layout.element.Table(UnitValue.createPercentArray(amountColumn))
                .useAllAvailableWidth();
    }

    public static TableDefaultBuilder builder() {
        return new TableDefaultBuilder();
    }

    public void addCell(String text, int rowspan, int colspan, Color color) {
        Cell cell = new Cell(rowspan, colspan).add(new Paragraph(text));
        cell.setBackgroundColor(color);
        table.addCell(cell);
    }

    private void addCell(Cell cell) {
        table.addCell(cell);
    }

    public static class TableDefaultBuilder {
        private int amountColumn;
        private final List<Cell> cells = new ArrayList<>();

        TableDefaultBuilder() {
        }

        public TableDefaultBuilder amountColumn(int amountColumn) {
            this.amountColumn = amountColumn;
            return this;
        }
        public TableDefaultBuilder addCell(String text, int rowspan, int colspan, Color color) {
            Cell cell = new Cell(rowspan, colspan).add(new Paragraph(text));
            cell.setBackgroundColor(color);
            this.cells.add(cell);
            return this;
        }
        public TableDefaultBuilder addCell(String text, Color color) {
            Cell cell = new Cell().add(new Paragraph(text));
            cell.setBackgroundColor(color);
            this.cells.add(cell);
            return this;
        }

        public TableDefault build() {
            TableDefault tableDefault= new TableDefault(amountColumn);
            this.cells.forEach(tableDefault::addCell);
            return tableDefault;
        }

        public String toString() {
            return "Table.TableBuilder(amountColumn=" + this.amountColumn + ", cells=" + this.cells + ")";
        }
    }
}
