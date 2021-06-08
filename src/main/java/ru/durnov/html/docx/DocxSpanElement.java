package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;

public class DocxSpanElement {
    private final XWPFRun xwpfRun;

    public DocxSpanElement(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public Element element() {
        Element element = new Element("span");
        if (xwpfRun.isHighlighted()){
            new DocxUElement(xwpfRun).element().appendTo(element);
        } else if (xwpfRun.isBold()) {
            new DocxStrongStrongElement(xwpfRun).element().appendTo(element);
        } else {
            element.appendText(xwpfRun.text());
        }
        return element;
    }
}
