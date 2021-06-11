package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.jsoup.nodes.Element;
import ru.durnov.html.docx.DocxChildElement;
import ru.durnov.html.docx.DocxParagraphStyle;

import java.util.List;

public class DocxTextContentElement implements DocxContentElement {
    private final XWPFParagraph xwpfParagraph;

    public DocxTextContentElement(IBodyElement bodyElement) {
        if (! (bodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException("BodyElement must be XWPFParagraph");
        this.xwpfParagraph = (XWPFParagraph) bodyElement;
    }

    @Override
    public Element element() {
        System.out.println(xwpfParagraph.getText());
        Element element = new Element("p");
        new DocxParagraphStyle(xwpfParagraph).applyToParagraphElement(element);
        this.xwpfParagraph.getRuns().forEach(xwpfRun -> {
            new DocxChildElement(xwpfRun).appendTo(element);
            List<XWPFPicture> embeddedPictures = xwpfRun.getEmbeddedPictures();
        });
        return element;
    }
}
