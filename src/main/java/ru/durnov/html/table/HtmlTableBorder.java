package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.nodes.Element;

public class HtmlTableBorder implements TableBorder {
    private final int borderSize;

    public HtmlTableBorder(int borderSize){
        this.borderSize = borderSize;
    }

    public HtmlTableBorder(XWPFTable xwpfTable) {
        this.borderSize = xwpfTable.getLeftBorderSize();
    }

    @Override
    public void applyBorderToElement(Element element) {
        System.out.println(borderSize);
        if (borderSize != -1) {
            int value = borderSize /8;
            if (value > 1){
                element.attributes().put("border", String.valueOf(value));
            } else {
                element.attributes().put("border", "1");
            }
        }
    }
}
