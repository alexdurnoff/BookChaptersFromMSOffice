package ru.durnov.html;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public class CellNumber {
    private final int number;

    public CellNumber(XWPFTableRow xwpfTableRow, XWPFTableCell xwpfTableCell) {
        int value = -1;
        List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
        for (int i = 0; i <tableCells.size(); i++){
            if (tableCells.get(i).equals(xwpfTableCell)) value = i;
        }
        this.number = value;
    }

    public int value() {
        return this.number;
    }
}
