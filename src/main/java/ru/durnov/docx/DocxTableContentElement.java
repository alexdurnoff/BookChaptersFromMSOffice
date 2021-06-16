package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jsoup.nodes.Element;
import ru.durnov.html.HtmlSkippingCellDetector;
import ru.durnov.html.table.HtmlTableStyle;
import ru.durnov.html.table.SkippingCellDetector;

import java.util.List;

public class DocxTableContentElement implements DocxContentElement {
    private final XWPFTable xwpfTable;
    private final SkippingCellDetector detector;

    public DocxTableContentElement(IBodyElement bodyElement) {
        if (! (bodyElement instanceof  XWPFTable)) throw new IllegalArgumentException("IBobyElement must be instance of XWPFTable");
        this.xwpfTable = (XWPFTable) bodyElement;
        this.detector = new HtmlSkippingCellDetector(xwpfTable);
    }

    @Override
    public Element element() {
        Element element = new Element("table");
        new HtmlTableStyle(xwpfTable).applyToTableElement(element);
        xwpfTable.getRows().forEach(xwpfTableRow -> {
            Element rowElement = new DocxTableRowElement(xwpfTableRow, detector).element();
            rowElement.appendTo(element);
        });
        return element;
    }
}
