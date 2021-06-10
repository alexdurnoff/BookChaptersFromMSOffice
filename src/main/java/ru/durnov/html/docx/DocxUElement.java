package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;

public class DocxUElement {
    private final XWPFRun xwpfRun;

    public DocxUElement(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public Element element(){
        Element element = new Element("u");
        if (xwpfRun.isBold()) {
            new DocxStrongStrongElement(xwpfRun).element().appendTo(element);
        } else {
            element.appendText(text());
        }
    return element;
    }

    protected String text(){
        return xwpfRun.text().replace("\n", "");
    }
}
