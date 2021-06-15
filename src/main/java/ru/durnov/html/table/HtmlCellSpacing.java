package ru.durnov.html.table;

import org.jsoup.nodes.Element;

public class HtmlCellSpacing implements CellSpacing {
    private final int cellSpacing;

    public HtmlCellSpacing(int cellSpacing) {
        this.cellSpacing = cellSpacing;
    }

    @Override
    public void applyCellSpacingToElement(Element element) {
        element.attributes().put("cellcpacing", String.valueOf(cellSpacing));
    }
}
