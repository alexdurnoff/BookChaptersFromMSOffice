package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;


public class DocElementFactory {
    private final Paragraph paragraph;

    public DocElementFactory(Paragraph paragraph) {
        this.paragraph = paragraph;
    }


    public DocContentElement docContentElement() {
        return null;
    }
}
