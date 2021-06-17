package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.jsoup.nodes.Element;

public class DocParagraphContentElement implements DocContentElement {
    public DocParagraphContentElement(Paragraph paragraph) {
    }

    @Override
    public Element element() {
        return null;
    }
}
