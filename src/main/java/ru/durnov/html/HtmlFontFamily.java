package ru.durnov.html;


import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class HtmlFontFamily {
    private final String fontFamily;

    public HtmlFontFamily(XWPFRun xwpfRun) {
        this.fontFamily = xwpfRun.getFontFamily();
    }

    public HtmlFontFamily(CharacterRun characterRun){
        this.fontFamily = characterRun.getFontName();
    }

    public String value() {
        if (this.fontFamily != null) return this.fontFamily;
        return "Times new Roman";
    }
}
