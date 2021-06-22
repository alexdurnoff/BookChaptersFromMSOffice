package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Table;
import org.jsoup.nodes.Element;

public class HtmlCellPadding implements CellPadding {
    private final int cellPadding;

    public HtmlCellPadding(int cellPadding) {
        this.cellPadding = cellPadding;
    }

    public HtmlCellPadding(Table table) {
        this.cellPadding = 0;
    }

    @Override
    public void applyCellPadingToElement(Element element) {
        element.attributes().put("cellpadding", String.valueOf(this.cellPadding));
    }
}
