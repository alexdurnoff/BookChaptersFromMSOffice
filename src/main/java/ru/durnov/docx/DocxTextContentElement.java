package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;

public class DocxTextContentElement implements DocxContentElement {
    private final XWPFParagraph xwpfParagraph;

    public DocxTextContentElement(IBodyElement bodyElement) {
        if (! (bodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException("BodyElement must be XWPFParagraph");
        this.xwpfParagraph = (XWPFParagraph) bodyElement;
    }

    @Override
    public Element element() {
        Element element = new Element("p");
        this.xwpfParagraph.getRuns().forEach(xwpfRun -> {
            Element child = new Element("span");
            child.appendText(xwpfRun.text());
            child.appendTo(element);
        });
        return element;
    }
}
