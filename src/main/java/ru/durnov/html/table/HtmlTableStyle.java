package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.nodes.Element;

public class HtmlTableStyle implements TableStyle {
    private final TableAlignment tableAlignment;
    private final TableWidth tableWidth;
    private final TableBorder tableBorder;
    private final TableBorderColor tableBorderColor;
    private final CellPadding cellPadding;
    private final CellSpacing cellSpacing;
    private final TableHeight tableHeight;



    public HtmlTableStyle(XWPFTable xwpfTable) {
        this.tableAlignment = new HtmlTableAlignment(xwpfTable.getTableAlignment());
        this.tableWidth = new HtmlTableWidth(xwpfTable);
        this.tableBorder = new HtmlTableBorder(xwpfTable);
        this.tableBorderColor = new HtmlTableBorderColor(xwpfTable);
        this.cellPadding = new HtmlCellPadding(xwpfTable.getCellMarginLeft()/20);
        this.cellSpacing = new HtmlCellSpacing(xwpfTable.getCellMarginLeft()/20);
        this.tableHeight = new HtmlTableHeight(xwpfTable);

    }



    @Override
    public void applyToTableElement(Element element) {
        element.attributes().put("style", "table-layout: fixed;");
        this.tableAlignment.applyAlignmentToElement(element);
        this.tableWidth.applyWidthToElement(element);
        this.tableBorder.applyBorderToElement(element);
        this.tableBorderColor.applyBorderColorToElement(element);
        this.cellPadding.applyCellPadingToElement(element);
        this.cellSpacing.applyCellSpacingToElement(element);
        this.tableHeight.applyHeightToElement(element);
    }
}
