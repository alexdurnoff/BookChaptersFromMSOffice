package ru.durnov.html.docx;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocxHtmlUnderLine {
    private final UnderlinePatterns patterns;

    public DocxHtmlUnderLine(XWPFRun xwpfRun) {
        this.patterns = xwpfRun.getUnderline();
    }



    public String value() {
        if (this.patterns == UnderlinePatterns.NONE) return "none";
        if (this.patterns == UnderlinePatterns.DASH) return "underline";
        if (this.patterns == UnderlinePatterns.DASH_DOT_DOT_HEAVY) return "underline";
        if (this.patterns == UnderlinePatterns.DASH_DOT_HEAVY) return "underline";
        if (this.patterns == UnderlinePatterns.DASH_LONG) return "underline";
        if (this.patterns == UnderlinePatterns.DASH_LONG_HEAVY) return "underline";
        if (this.patterns == UnderlinePatterns.DASHED_HEAVY) return "underline";
        if (this.patterns == UnderlinePatterns.THICK) return "underline";
        if (this.patterns == UnderlinePatterns.DOUBLE ) return "underline";
        if (this.patterns == UnderlinePatterns.SINGLE) return "underline";
        return "none";
    }
}
