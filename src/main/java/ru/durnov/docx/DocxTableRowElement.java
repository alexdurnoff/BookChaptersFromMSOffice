package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jsoup.nodes.Element;
import ru.durnov.html.CellCoordinates;
import ru.durnov.html.HtmlSkippingCellDetector;
import ru.durnov.html.table.SkippingCellDetector;

public class DocxTableRowElement {
    private final XWPFTableRow xwpfTableRow;
    private final SkippingCellDetector detector;

    public DocxTableRowElement(XWPFTableRow xwpfTableRow) {
        this.xwpfTableRow = xwpfTableRow;
        this.detector = new HtmlSkippingCellDetector(xwpfTableRow);
    }

    public DocxTableRowElement(XWPFTableRow xwpfTableRow, SkippingCellDetector skippingCellDetector){
        this.xwpfTableRow = xwpfTableRow;
        this.detector = skippingCellDetector;
    }

    public Element element() {
        Element rowElement = new Element("tr");
        this.xwpfTableRow.getTableCells().forEach(xwpfTableCell -> {
            if (! detector.cellMustBeSkipped(new CellCoordinates(xwpfTableCell))) {
                Element cellElement = new DocxTableCellElement(xwpfTableCell).element();
                cellElement.appendTo(rowElement);
            }
        });
        return rowElement;
    }
}
