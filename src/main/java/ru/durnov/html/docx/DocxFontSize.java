package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocxFontSize {
    private final XWPFRun xwpfRun;

    public DocxFontSize(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public int value() {
        if (xwpfRun.getFontSize() != -1) return xwpfRun.getFontSize();
        return 12;
    }
}
