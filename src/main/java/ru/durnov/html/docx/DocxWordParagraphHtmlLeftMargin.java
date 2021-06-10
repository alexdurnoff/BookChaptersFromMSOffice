package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocxWordParagraphHtmlLeftMargin {
    private final XWPFParagraph xwpfParagraph;

    public DocxWordParagraphHtmlLeftMargin(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
    }

    public int value() {
        return this.xwpfParagraph.getFirstLineIndent();
    }
}
