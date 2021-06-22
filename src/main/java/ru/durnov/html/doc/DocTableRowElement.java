package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.jsoup.nodes.Element;
import ru.durnov.doc.ParagraphWithSection;
import ru.durnov.html.CellCoordinates;
import ru.durnov.html.table.SkippingCellDetector;

public class DocTableRowElement {
    private final TableRow row;
    private final Table table;
    private final SkippingCellDetector detector;
    private final int rowNumber;


    public DocTableRowElement(TableRow row,
                              Table table,
                              SkippingCellDetector detector,
                              int rowNumber) {
        this.row = row;
        this.table = table;
        this.detector = detector;
        this.rowNumber = rowNumber;
    }

    public Element element() {
        Element rowElement = new Element("tr");
        int numCells = row.numCells();
        for (int i = 0; i < numCells; i++){
            TableCell cell = row.getCell(i);
            if (!detector.cellMustBeSkipped(new CellCoordinates(i, rowNumber))){
                Element cellElement = new DocTableCellElement(cell, row, table).element();
                cellElement.appendTo(rowElement);
            }
        }
        return rowElement;
    }
}
