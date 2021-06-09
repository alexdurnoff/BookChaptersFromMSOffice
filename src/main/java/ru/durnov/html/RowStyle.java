package ru.durnov.html;

import org.jsoup.nodes.Element;

public interface RowStyle {
    void applyToTableRowElement(Element element);
}
