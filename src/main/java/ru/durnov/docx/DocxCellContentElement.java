package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.html.docx.DocxChildElement;
import ru.durnov.html.docx.DocxParagraphStyle;
import ru.durnov.html.docx.HtmlDivStyle;

public class DocxCellContentElement implements DocxContentElement {
    private final XWPFParagraph xwpfParagraph;


    public DocxCellContentElement(IBodyElement bodyElement){
        if (! (bodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException("BodyElement must be XWPFParagraph");
        this.xwpfParagraph = (XWPFParagraph) bodyElement;

    }


    @Override
    public Element element() {
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
        element.attributes().remove("style");
        return element;
    }
}
