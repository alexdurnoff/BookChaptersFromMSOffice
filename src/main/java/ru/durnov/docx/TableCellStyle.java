package ru.durnov.docx;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
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
    private final RowSpan rowSpan;

    public TableCellStyle(BackGroundColor backGroundColor,
                          TableVerticalAlignment tableVerticalAlignment,
                          TableHeight tableHeight,
                          TableWidth tableWidth,
                          ColSpan colSpan,
                          RowSpan rowSpan) {
        this.backGroundColor = backGroundColor;
        this.tableVerticalAlignment = tableVerticalAlignment;
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.colSpan = colSpan;
        this.rowSpan = rowSpan;
    }

    public TableCellStyle(XWPFTableCell xwpfTableCell) {
        this.backGroundColor = new HtmlBackGroundColor(xwpfTableCell.getColor());
        this.tableVerticalAlignment = new HtmlTableVerticalAlignment(xwpfTableCell.getVerticalAlignment());
        this.tableHeight = new HtmlTableHeight(xwpfTableCell.getTableRow().getHeight());
        this.tableWidth = new HtmlTableWidth(xwpfTableCell);
        this.colSpan = new HtmlColSpan(xwpfTableCell);
        this.rowSpan = new HtmlRowSpan(xwpfTableCell);
    }

    public TableCellStyle(TableCell cell, TableRow tableRow, Table table) {
        this.backGroundColor = new HtmlBackGroundColor("auto");
        this.tableVerticalAlignment = new HtmlTableVerticalAlignment(cell.getVertAlign());
        this.tableHeight = new HtmlTableHeight(-1);
        this.tableWidth = new HtmlTableWidth(cell.getWidth());
        this.colSpan = new HtmlColSpan(cell, tableRow, table);
        this.rowSpan = new HtmlRowSpan(1);
    }

    @Override
    public void applyToTableCellElement(Element element) {
        this.backGroundColor.applyBackGroundColorToElement(element);
        this.tableVerticalAlignment.applyTableVerticalAlignmentToElement(element);
        this.tableHeight.applyHeightToElement(element);
        this.tableWidth.applyWidthToElement(element);
        this.colSpan.applyCollSpanToElement(element);
        this.rowSpan.applyRowSpanToElement(element);
    }
}
