package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocxFontFamily {
    private final XWPFRun xwpfRun;

    public DocxFontFamily(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public String value() {
        if (xwpfRun.getFontFamily() != null) return xwpfRun.getFontFamily();
        return "Times new Roman";
    }
}
