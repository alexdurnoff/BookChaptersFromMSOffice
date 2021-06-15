package ru.durnov.html.table;

import org.jsoup.nodes.Element;

public class HtmlCellPadding implements CellPadding {
    private final int cellPadding;

    public HtmlCellPadding(int cellPadding) {
        this.cellPadding = cellPadding;
    }

    @Override
    public void applyCellPadingToElement(Element element) {
        element.attributes().put("cellpadding", String.valueOf(this.cellPadding));
    }
}
