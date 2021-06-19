package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.docx.DocxHtmlHiperLinkStyle;
import ru.durnov.html.docx.DocxSpanElement;


public class DocxChildElementFromHiperLink {
    private final XWPFHyperlinkRun xwpfHyperlinkRun;

    public DocxChildElementFromHiperLink(XWPFHyperlinkRun xwpfHyperlinkRun) {
        this.xwpfHyperlinkRun = xwpfHyperlinkRun;
    }

    public void appendTo(Element element) {
        Element child = new DocxSpanElement(xwpfHyperlinkRun).element();
        new DocxHtmlHiperLinkStyle(xwpfHyperlinkRun).applyToRunElement(child);
        child.appendTo(element);
    }
}
