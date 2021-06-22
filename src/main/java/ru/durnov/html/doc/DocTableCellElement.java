package ru.durnov.html.doc;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.jsoup.nodes.Element;
import ru.durnov.docx.TableCellStyle;

public class DocTableCellElement {
    private final TableCell cell;
    private final TableRow tableRow;
    private final Table table;

    public DocTableCellElement(TableCell cell, TableRow tableRow, Table table) {
        this.cell = cell;
        this.tableRow = tableRow;
        this.table = table;
    }

    public Element element() {
        Element cellElement = new Element("td");
        new TableCellStyle(cell, tableRow, table).applyToTableCellElement(cellElement);
        return null;
    }
}
