package ru.durnov.html;

import org.jsoup.nodes.Element;
import ru.durnov.html.table.BackGroundColor;

public class HtmlBackGroundColor implements BackGroundColor {
    private final String color;

    public HtmlBackGroundColor(String color) {
        this.color = color;
    }

    @Override
    public void applyBackGroundColorTOElement(Element element) {
        if (color != null && (!color.equals(""))) {
            element.attributes().put("bgcolor", color);
        }
    }
}
