package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.BorderCode;
import org.apache.poi.hwpf.usermodel.Table;
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

    public HtmlTableBorder(Table table){
        BorderCode topBorder = table.getRow(0).getTopBorder();
        this.borderSize = topBorder.getLineWidth();
    }

    @Override
    public void applyBorderToElement(Element element) {
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
