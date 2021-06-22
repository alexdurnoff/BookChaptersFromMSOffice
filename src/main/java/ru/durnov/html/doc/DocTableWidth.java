package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableRow;

public class DocTableWidth {
    private final int width;

    public DocTableWidth(Table table) {
        int summ = 0;
        int rowNumbers = table.numRows();
        for (int i = 0; i < rowNumbers; i++){
            TableRow row = table.getRow(i);
            int cellNumbers = row.numCells();
            int rowSummWidth = 0;
            for (int j = 0; j < cellNumbers; j++){
                rowSummWidth += row.getCell(j).getWidth();
            }
            if (rowSummWidth > summ) summ = rowSummWidth;
        }
        this.width = summ;
    }

    public String width() {
        return String.valueOf(this.width);
    }
}
