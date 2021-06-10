package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocxWordParagraphHtmlTopMargin {
    private final XWPFParagraph xwpfParagraph;

    public DocxWordParagraphHtmlTopMargin(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
    }

    public int value() {
        return this.xwpfParagraph.getSpacingBefore();
    }
}
