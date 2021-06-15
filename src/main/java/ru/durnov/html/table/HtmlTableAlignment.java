package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.jsoup.nodes.Element;

public class HtmlTableAlignment implements TableAlignment {
    private final String alignment;

    public HtmlTableAlignment(TableRowAlign tableAlignment) {
        if (tableAlignment != null) {
            if (tableAlignment.getValue() == 1) {
                this.alignment = "left";
            } else if (tableAlignment.getValue() == 2) {
                this.alignment = "center";
            } else {
                this.alignment = "right";
            }
        } else {
            this.alignment = "center";
        }
    }

    @Override
    public void applyAlignmentToElement(Element element) {
        element.attributes().put("align", alignment);
    }
}
