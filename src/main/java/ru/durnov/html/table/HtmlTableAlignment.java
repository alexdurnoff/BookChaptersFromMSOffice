package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Paragraph;
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

    public HtmlTableAlignment(Paragraph paragraph) {
        int fontAlignment = paragraph.getFontAlignment();
        if (fontAlignment == 1) {
            this.alignment = "left";
        } else if (fontAlignment == 2) {
            this.alignment = "center";
        } else if (fontAlignment == 3) {
            this.alignment = "right";
        } else {
            this.alignment = "center";
        }
    }

    @Override
    public void applyAlignmentToElement(Element element) {
        element.attributes().put("align", alignment);
    }
}
