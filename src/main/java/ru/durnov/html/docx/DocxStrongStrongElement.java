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
            uElement.appendTo(element);

        } else {
            element.appendText(text());
        }
        return element;
    }

    protected String text(){
        return xwpfRun.text().replace("\n", "");
    }
}
