package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import ru.durnov.html.docx.DocxChildElement;
import ru.durnov.html.docx.DocxParagraphStyle;
import ru.durnov.html.docx.HtmlMargin;

public class DocxTextContentElement implements DocxContentElement {
    private final XWPFParagraph xwpfParagraph;
    private final CTPageMar ctPageMar;

    public DocxTextContentElement(IBodyElement bodyElement){
        if (! (bodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException("BodyElement must be XWPFParagraph");
        this.xwpfParagraph = (XWPFParagraph) bodyElement;
        this.ctPageMar = null;
    }

    public DocxTextContentElement(IBodyElement bodyElement, CTPageMar ctPageMar) {
        if (! (bodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException("BodyElement must be XWPFParagraph");
        this.xwpfParagraph = (XWPFParagraph) bodyElement;
        this.ctPageMar = ctPageMar;
    }

    @Override
    public Element element() {
        Element div  = new Element("div");
        new HtmlMargin(xwpfParagraph, ctPageMar).applyTo(div);
        Element element = new Element("p");
        new DocxParagraphStyle(xwpfParagraph).applyToParagraphElement(element);
        xwpfParagraph.getIRuns().forEach(iRunElement -> {
            if (iRunElement instanceof XWPFHyperlinkRun){
                XWPFHyperlinkRun xwpfHyperlinkRun = (XWPFHyperlinkRun) iRunElement;
                new DocxChildElementFromHiperLink(xwpfHyperlinkRun).appendTo(element);
            } else if (iRunElement instanceof XWPFRun) {
                XWPFRun xwpfRun = (XWPFRun) iRunElement;
                new DocxChildElement(xwpfRun).appendTo(element);
            }
        });
        element.appendTo(div);
        return div;
    }
}
