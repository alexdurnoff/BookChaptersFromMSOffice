package ru.durnov.html;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import ru.durnov.html.table.SkippingCellDetector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HtmlSkippingCellDetector implements SkippingCellDetector {
    private final Set<CellCoordinates> cellCoordinatesSet = new HashSet<>();

    public HtmlSkippingCellDetector(XWPFTableRow xwpfTableRow) {
        this(xwpfTableRow.getTable());
    }

    public HtmlSkippingCellDetector(XWPFTable xwpfTable) {
        List<XWPFTableRow> rows = xwpfTable.getRows();
        for (int i = 0; i < rows.size(); i++){
            XWPFTableRow xwpfTableRow = rows.get(i);
            List<XWPFTableCell> tableCells = xwpfTableRow.getTableCells();
            for (int j = 0; j < tableCells.size(); j++){
                XWPFTableCell xwpfTableCell = tableCells.get(j);
                try {
                    int rowSpan = xwpfTableCell.getCTTc().getTcPr().getVMerge().getVal().intValue();
                    if (rowSpan > 0){
                        for (int k = 1; k <= rowSpan; k++){
                            this.cellCoordinatesSet.add(
                                    new CellCoordinates(j, i + k)
                            );
                        }
                    }
                } catch (NullPointerException ignored) {

                }
            }
        }
    }

    @Override
    public boolean cellMustBeSkipped(CellCoordinates cellCoordinates) {
        return this.cellCoordinatesSet.contains(cellCoordinates);
    }

}
