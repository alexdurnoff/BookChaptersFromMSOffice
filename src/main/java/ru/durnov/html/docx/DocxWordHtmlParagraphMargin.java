package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import ru.durnov.html.ParagraphStyle;

import java.math.BigInteger;
import java.util.List;

public class DocxWordHtmlParagraphMargin implements ParagraphStyle {
    private final XWPFParagraph xwpfParagraph;

    public DocxWordHtmlParagraphMargin(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
    }

    @Override
    public void applyToParagraphElement(Element element) {

    }
}
