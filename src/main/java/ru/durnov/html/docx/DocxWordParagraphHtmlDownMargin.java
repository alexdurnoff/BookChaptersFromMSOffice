package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocxWordParagraphHtmlDownMargin {
    private final XWPFParagraph xwpfParagraph;

    public DocxWordParagraphHtmlDownMargin(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
    }

    public int value() {
        return this.xwpfParagraph.getSpacingAfter();
    }
}
