package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocxWordParagraphHtmlRightMargin {
    private final XWPFParagraph xwpfParagraph;

    public DocxWordParagraphHtmlRightMargin(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
    }

    public int value() {
        return this.xwpfParagraph.getIndentationRight();
    }
}
