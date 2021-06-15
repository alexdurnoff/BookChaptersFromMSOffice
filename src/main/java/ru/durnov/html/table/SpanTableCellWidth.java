package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.util.List;

public class SpanTableCellWidth {
    private final List<Integer> widthList;
    private final TableWidthType tableWidthType;

    public SpanTableCellWidth(List<Integer> widthList, TableWidthType tableWidthType){
        this.widthList = widthList;
        this.tableWidthType = tableWidthType;
    }

    public SpanTableCellWidth(XWPFTableCell xwpfTableCell) {
        this.widthList = new MaxCellNumberTableCellList(xwpfTableCell).widthList();
        this.tableWidthType = xwpfTableCell.getWidthType();
    }

    public String width() {
        int result = this.widthList.stream().mapToInt(integer -> integer).sum();
        if (this.tableWidthType == TableWidthType.PCT) return (result/50 + "%");
        return String.valueOf(result/20);
    }
}
