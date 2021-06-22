package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Table;
import org.jsoup.nodes.Element;

public class HtmlCellSpacing implements CellSpacing {
    private final int cellSpacing;

    public HtmlCellSpacing(int cellSpacing) {
        this.cellSpacing = cellSpacing;
    }

    public HtmlCellSpacing(Table table) {
        this.cellSpacing = 0;
    }

    @Override
    public void applyCellSpacingToElement(Element element) {
        element.attributes().put("cellcpacing", String.valueOf(cellSpacing));
    }
}
