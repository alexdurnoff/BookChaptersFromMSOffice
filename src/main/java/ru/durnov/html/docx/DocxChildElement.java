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
        child.appendTo(element);
        if (xwpfRun.text().endsWith("\n")){
            System.out.println("DETECTED END OF LINE");
            new Element("br").appendTo(element);
        }
    }
}
