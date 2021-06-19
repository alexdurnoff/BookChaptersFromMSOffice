package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DocxFontColor {
    private final String color;
    private final String text;

    public DocxFontColor(XWPFRun xwpfRun){
        this.color ="#" + xwpfRun.getColor();
        this.text = xwpfRun.text();
    }

    String value(){
        if (text.startsWith("http://") || text.startsWith("https://")){
            if (! text.contains(",") && !(text.contains(" "))) return "navy";
        }
        if (color.equals("#null")) return "auto";
        return  this.color.toLowerCase();
    }
}
