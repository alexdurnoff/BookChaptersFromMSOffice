package ru.durnov.html;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class HtmlFontSize {
    private final int fontSize;

    public HtmlFontSize(XWPFRun xwpfRun) {
        this.fontSize = xwpfRun.getFontSize();
    }

    public HtmlFontSize(CharacterRun characterRun){
        this.fontSize = characterRun.getFontSize();
    }

    public int value() {
        if (this.fontSize != -1) return this.fontSize;
        return 12;
    }
}
