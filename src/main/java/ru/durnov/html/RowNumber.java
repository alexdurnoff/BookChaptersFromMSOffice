package ru.durnov.html;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public class RowNumber {
    private final int number;

    public RowNumber(XWPFTable xwpfTable, XWPFTableRow xwpfTableRow) {
        int value = -1;
        List<XWPFTableRow> rows = xwpfTable.getRows();
        for (int i = 0; i < rows.size(); i++){
            if (rows.get(i).equals(xwpfTableRow)) value = i;
        }
        this.number = value;
    }

    public int value(){
        return this.number;
    }


}
