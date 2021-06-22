package ru.durnov.html;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlFontColor {
    private final String color;
    private final String text;

    public HtmlFontColor(XWPFRun xwpfRun){
        this.color ="#" + xwpfRun.getColor();
        this.text = xwpfRun.text();
    }

    public HtmlFontColor(CharacterRun characterRun){
        this.color = "#" + characterRun.getColor();
        this.text = characterRun.text();
    }

    public String value(){
        if (text.startsWith("http://") || text.startsWith("https://")){
            if (! text.contains(",") && !(text.contains(" "))) return "navy";
        }
        if (color.equals("#null")) return "auto";
        return  this.color.toLowerCase();
    }
}
