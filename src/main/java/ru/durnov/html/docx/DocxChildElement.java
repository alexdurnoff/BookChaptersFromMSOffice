package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;

public class DocxChildElement {
    private final XWPFRun xwpfRun;

    public DocxChildElement(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public void appendTo(Element element) {
        Element child = new DocxSpanElement(xwpfRun).element();
        new DocxHtmlRunStyle(xwpfRun).applyToRunElement(element);
        if (xwpfRun.isBold()){
            Element strongElement = new Element("strong");
            if (xwpfRun.isHighlighted()) {
                Element uElement = new Element("u");
                uElement.appendText(xwpfRun.text());
                uElement.appendTo(strongElement);
            } else {
                strongElement.appendText(xwpfRun.text());
            }
        } else if (xwpfRun.isHighlighted()) {
            Element uElement = new Element("u");
            uElement.appendText(xwpfRun.text());
            uElement.appendTo(child);
        }
        child.appendTo(element);
    }
}
