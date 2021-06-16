package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import ru.durnov.html.docx.DocxCellNumberColumn;
import ru.durnov.html.docx.DocxMaxCellNumberTableRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс будет возвращать список Integer, соответствующий ширине ячеек
 * строки с самым большим количеством ячеек. В единственном методе возвращает список
 * Integer начиная с номера столбца ячейки, которая объединяет несколько ячеек в одну.
 */
public class MaxCellNumberTableCellList {
    private final int startNumber;
    private final int size;
    private final List<Integer> widthList;

    public MaxCellNumberTableCellList(int startNumber, List<Integer> widthList) {
        this.startNumber = startNumber;
        this.widthList = widthList;
        this.size = widthList.size();
    }

    public MaxCellNumberTableCellList(XWPFTableCell xwpfTableCell) {
        this.widthList = new DocxMaxCellNumberTableRow(xwpfTableCell).widthList();
        this.size = this.widthList.size();
        this.startNumber = new DocxCellNumberColumn(xwpfTableCell).column();
    }

    public List<Integer> widthValuesList() {
        return this.widthList.subList(startNumber, this.size);
    }
}
