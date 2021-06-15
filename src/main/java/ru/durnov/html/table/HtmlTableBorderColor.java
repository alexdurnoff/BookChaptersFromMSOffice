package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.nodes.Element;

public class HtmlTableBorderColor implements TableBorderColor {
    private final String color;

    public HtmlTableBorderColor(XWPFTable xwpfTable) {
        if (xwpfTable.getLeftBorderColor() != null) {
            if (xwpfTable.getLeftBorderColor().equals("auto")){
                this.color = "black";//Иначе нам красный рисует
            } else {
                this.color = xwpfTable.getLeftBorderColor();
            }
        } else {
            this.color = "none";
        }

    }

    @Override
    public void applyBorderColorToElement(Element element) {
        element.attributes().put("bordercolor", this.color);
    }
}
