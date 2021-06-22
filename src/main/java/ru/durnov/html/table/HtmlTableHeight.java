package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jsoup.nodes.Element;
import ru.durnov.html.table.TableHeight;

import java.util.List;

public class HtmlTableHeight implements TableHeight {
    private final int height;

    public HtmlTableHeight(int height){
        this.height = height;
    }

    public HtmlTableHeight(XWPFTable xwpfTable) {
        int sum = 0;
        List<XWPFTableRow> rows = xwpfTable.getRows();
        for (XWPFTableRow row : rows) {
            sum += row.getHeight();
            sum += xwpfTable.getTopBorderSize();
        }
        this.height = sum;
    }

    public HtmlTableHeight(Table table){
        int sum = 0;
        int rowNumber = table.numRows();
        for (int i = 0; i < rowNumber; i++){
            TableRow row = table.getRow(i);
            sum += row.getRowHeight();
            sum += row.getTopBorder().getLineWidth();
            sum += row.getBottomBorder().getLineWidth();
        }
        this.height = sum;
    }

    @Override
    public void applyHeightToElement(Element element) {
        if (height > 0) element.attributes().put("height", String.valueOf(height));
    }
}
