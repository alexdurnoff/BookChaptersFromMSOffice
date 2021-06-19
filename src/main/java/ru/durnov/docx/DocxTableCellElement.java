package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;

public class DocxTableCellElement {
    private final XWPFTableCell xwpfTableCell;


    public DocxTableCellElement(XWPFTableCell xwpfTableCell) {
        this.xwpfTableCell = xwpfTableCell;

    }

    public Element element() {
        Element cellElement = new Element("td");
        new TableCellStyle(xwpfTableCell).applyToTableCellElement(cellElement);
        xwpfTableCell.getBodyElements().forEach(bodyElement -> {
            new DocxElementFactory(bodyElement)
                    .docxContentElement()
                    .element()
                    .appendTo(cellElement);
        });
        return cellElement;
    }
}
