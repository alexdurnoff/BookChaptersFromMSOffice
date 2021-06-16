package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;
import ru.durnov.html.CellStyle;
import ru.durnov.html.HtmlBackGroundColor;
import ru.durnov.html.table.*;

public class TableCellStyle implements CellStyle {
    private final BackGroundColor backGroundColor;
    private final TableVerticalAlignment tableVerticalAlignment;
    private final TableHeight tableHeight;
    private final TableWidth tableWidth;
    private final ColSpan colSpan;

    public TableCellStyle(BackGroundColor backGroundColor,
                          TableVerticalAlignment tableVerticalAlignment,
                          TableHeight tableHeight,
                          TableWidth tableWidth,
                          ColSpan colSpan) {
        this.backGroundColor = backGroundColor;
        this.tableVerticalAlignment = tableVerticalAlignment;
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.colSpan = colSpan;
    }

    public TableCellStyle(XWPFTableCell xwpfTableCell) {
        this.backGroundColor = new HtmlBackGroundColor(xwpfTableCell.getColor());
        this.tableVerticalAlignment = new HtmlTableVerticalAlignment(xwpfTableCell.getVerticalAlignment());
        this.tableHeight = new HtmlTableHeight(xwpfTableCell.getTableRow().getHeight());
        this.tableWidth = new HtmlTableWidth(xwpfTableCell);
        this.colSpan = new HtmlColSpan(xwpfTableCell);
    }

    @Override
    public void applyToTableCellElement(Element element) {
        this.backGroundColor.applyBackGroundColorTOElement(element);
        this.tableVerticalAlignment.applyTableVerticalAlignmentToElement(element);
        this.tableHeight.applyHeightToElement(element);
        this.tableWidth.applyWidthToElement(element);
        this.colSpan.applyCollSpanToElement(element);
    }
}
