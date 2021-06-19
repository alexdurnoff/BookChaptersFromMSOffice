package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;

public class DocxFontColor {
    private final String color;

    public DocxFontColor(XWPFRun xwpfRun){
        this.color = xwpfRun.getColor();
        System.out.println(color);

    }

    String value(){
        if (color == null) return "auto";
        return "#" + this.color.toLowerCase();
    }
}
