package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * Класс возвращает номер столбца в строке таблицы по ячейке
 */
public class DocxCellNumberColumn {
    private final int columnNumber;

    public DocxCellNumberColumn(XWPFTableCell xwpfTableCell) {
        XWPFTableRow xwpfTableRow = xwpfTableCell.getTableRow();
        int value = -1;
        List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
        for (int i = 0; i < tableCells.size(); i++){
            if (tableCells.get(i).equals(xwpfTableCell)) value = i;
        }
        if (value == -1) throw new IllegalArgumentException("This XWPFTableCell doesn't belong to a XWPFTableRow");
        this.columnNumber = value;
    }

    public int column() {
        return this.columnNumber;
    }
}
