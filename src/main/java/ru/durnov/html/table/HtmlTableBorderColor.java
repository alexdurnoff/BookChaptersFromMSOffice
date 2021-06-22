package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Table;
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
            this.color = "black";
        }

    }

    public HtmlTableBorderColor(Table table) {
        short colorIntValue = table.getRow(0).getTopBorder().getColor();
        if (colorIntValue == 0) {
            this.color = "auto";
        } else if (colorIntValue == 1){
            this.color = "black";
        } else if (colorIntValue == 2) {
            this.color = "blue";
        } else if (colorIntValue == 3) {
            this.color = "cyan";
        } else if (colorIntValue == 4) {
            this.color = "green";
        } else if (colorIntValue == 5) {
            this.color = "magenta";
        } else if (colorIntValue == 6) {
            this.color = "red";
        } else if (colorIntValue == 7) {
            this.color = "yellow";
        } else if (colorIntValue == 8) {
            this.color = "white";
        } else if (colorIntValue == 9) {
            this.color = "blue";
        } else if (colorIntValue == 10) {
            this.color = "cyan";
        } else if (colorIntValue == 11) {
            this.color = "green";
        } else if (colorIntValue == 12) {
            this.color = "magenta";
        } else if (colorIntValue == 13) {
            this.color = "red";
        } else if (colorIntValue == 14) {
            this.color = "yellow";
        } else if (colorIntValue == 15) {
            this.color = "gray";
        } else if (colorIntValue == 16) {
            this.color = "gray";
        } else {
            this.color = "none";
        }
    }

    @Override
    public void applyBorderColorToElement(Element element) {
        element.attributes().put("bordercolor", this.color);
    }
}
