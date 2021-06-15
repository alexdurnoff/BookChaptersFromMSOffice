package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;

public class DocxMaxCellNumberTableRow {
    private final XWPFTableRow xwpfTableRow;

    public DocxMaxCellNumberTableRow(XWPFTableCell xwpfTableCell) {
        XWPFTable xwpfTable = xwpfTableCell.getTableRow().getTable();
        List<XWPFTableRow> rows = xwpfTable.getRows();
        XWPFTableRow tableRow = rows.get(0);
        for (XWPFTableRow row : rows) {
            if (row.getTableCells().size() > tableRow.getTableCells().size()){
                tableRow = row;
            }
        }
        this.xwpfTableRow = tableRow;
    }

    public List<Integer> widthList() {
        List<Integer> widthList = new ArrayList<>();
        xwpfTableRow.getTableCells().forEach(xwpfTableCell -> {
            widthList.add(xwpfTableCell.getWidth());
        });
        return widthList;
    }
}
