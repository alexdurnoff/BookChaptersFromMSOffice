package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;

public class DocxChildElement {
    private final XWPFRun xwpfRun;

    public DocxChildElement(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public void appendTo(Element element) {
        if (xwpfRun.getEmbeddedPictures().size() > 0) {
            new PicturesElements(xwpfRun).elements().forEach(e ->{
                e.appendTo(element);
            });
        } else {
            Element child = new DocxSpanElement(xwpfRun).element();
            new DocxHtmlRunStyle(xwpfRun).applyToRunElement(child);
            child.appendTo(element);
            if (xwpfRun.text().endsWith("\n")){
                new Element("br").appendTo(element);
            }
        }
    }
}
