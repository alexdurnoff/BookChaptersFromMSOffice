package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class NonSpanTableCellWidth{
    private final XWPFTableCell xwpfTableCell;

    public NonSpanTableCellWidth(XWPFTableCell xwpfTableCell) {
        this.xwpfTableCell = xwpfTableCell;
    }

    String value(){
        if (xwpfTableCell.getWidthType() == TableWidthType.PCT){
            return xwpfTableCell.getWidth() / 50 + "%";
        } else {
            return String.valueOf(xwpfTableCell.getWidth() / 20);
        }
    }
}
