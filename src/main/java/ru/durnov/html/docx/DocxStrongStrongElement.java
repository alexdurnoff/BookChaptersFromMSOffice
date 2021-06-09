package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;

public class DocxStrongStrongElement {
    private final XWPFRun xwpfRun;

    public DocxStrongStrongElement(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public Element element(){
        Element element = new Element("strong");
        if (xwpfRun.isHighlighted()) {
            Element uElement = new DocxUElement(xwpfRun).element();
            element.appendTo(element);

        } else {
            element.appendText(xwpfRun.text());
        }
        return element;
    }
}
