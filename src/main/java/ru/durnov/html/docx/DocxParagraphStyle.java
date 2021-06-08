package ru.durnov.html.docx;

import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.html.ParagraphStyle;

public class DocxParagraphStyle implements ParagraphStyle {
    private final XWPFParagraph xwpfParagraph;

    public DocxParagraphStyle(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
    }

    @Override
    public void applyToParagraph() {


    }
}
