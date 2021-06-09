package ru.durnov.html;

import org.jsoup.nodes.Element;

public interface CellStyle {
    void applyToTableCellElement(Element element);
}
