package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.jsoup.nodes.Element;
import ru.durnov.html.docx.DocxHtmlTableStyle;

public class DocxTableContentElement implements DocxContentElement {
    private final XWPFTable xwpfTable;

    public DocxTableContentElement(IBodyElement bodyElement) {
        if (! (bodyElement instanceof  XWPFTable)) throw new IllegalArgumentException("IBobyElement must be instance of XWPFTable");
        this.xwpfTable = (XWPFTable) bodyElement;
    }

    @Override
    public Element element() {
        Element element = new Element("table");
        new DocxHtmlTableStyle(xwpfTable).applyToTableElement(element);
        xwpfTable.getRows().forEach(xwpfTableRow -> {
            Element rowElement = new DocxTableRowElement(xwpfTableRow).element();
            rowElement.appendTo(element);
        });
        return element;
    }
}
