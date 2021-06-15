package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jsoup.nodes.Element;

public class DocxTableRowElement {
    private final XWPFTableRow xwpfTableRow;
    public DocxTableRowElement(XWPFTableRow xwpfTableRow) {
        this.xwpfTableRow = xwpfTableRow;
    }

    public Element element() {
        Element rowElement = new Element("tr");
        this.xwpfTableRow.getTableCells().forEach(xwpfTableCell -> {
            Element cellElement = new DocxTableCellElement(xwpfTableCell).element();
            cellElement.appendTo(rowElement);
        });
        return rowElement;
    }
}
