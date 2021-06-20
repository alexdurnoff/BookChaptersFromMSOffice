package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import ru.durnov.html.ParagraphStyle;
import ru.durnov.html.ParagraphStyleParameters;

public class DocxParagraphStyle implements ParagraphStyle {
    private final XWPFParagraph xwpfParagraph;
    private final CTPageMar ctPageMar;

    public DocxParagraphStyle(XWPFParagraph xwpfParagraph, CTPageMar ctPageMar) {
        this.xwpfParagraph = xwpfParagraph;
        this.ctPageMar = ctPageMar;
    }

    public DocxParagraphStyle(XWPFParagraph xwpfParagraph) {
        this.xwpfParagraph = xwpfParagraph;
        this.ctPageMar = null;
    }


    @Override
    public void applyToParagraphElement(Element element) {
        new ParagraphStyleParameters(xwpfParagraph).applyToParagraphElement(element);
    }
}
