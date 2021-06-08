package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.nodes.Element;

public class DocxTableTextContentElement implements DocxContentElement {
    private final XWPFTable xwpfTable;

    public DocxTableTextContentElement(IBodyElement bodyElement) {
        if (! (bodyElement instanceof  XWPFTable)) throw new IllegalArgumentException("IBobyElement must be instance of XWPFTable");
        this.xwpfTable = (XWPFTable) bodyElement;
    }

    @Override
    public Element element() {
        return new Element("table");
    }//Пока так
}
