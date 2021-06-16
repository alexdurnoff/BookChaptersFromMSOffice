package ru.durnov.html;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.Objects;

public class CellCoordinates {
    private final int cell;
    private final int row;

    public CellCoordinates(int cell, int row) {
        this.cell = cell;
        this.row = row;
    }

    public CellCoordinates(XWPFTableCell xwpfTableCell){
        XWPFTableRow xwpfTableRow = xwpfTableCell.getTableRow();
        XWPFTable xwpfTable = xwpfTableRow.getTable();
        this.row = new RowNumber(xwpfTable, xwpfTableRow).value();
        this.cell = new CellNumber(xwpfTableRow, xwpfTableCell).value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellCoordinates that = (CellCoordinates) o;
        return cell == that.cell && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cell, row);
    }

    @Override
    public String toString() {
        return "CellCoordinates{" +
                "cell=" + cell +
                ", row=" + row +
                '}';
    }
}
