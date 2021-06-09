package ru.durnov.html.docx;

import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import ru.durnov.html.ParagraphStyle;
import ru.durnov.html.WordHtmlFont;
import ru.durnov.html.WordHtmlParagraphAlign;

public class DocxParagraphStyle implements ParagraphStyle {
    private final XWPFParagraph xwpfParagraph;

    public DocxParagraphStyle(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;

    }


    @Override
    public void applyToParagraphElement(Element element) {
        new WordHtmlParagraphAlign(xwpfParagraph).applyToElement(element);
    }
}
