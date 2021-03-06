package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;
import ru.durnov.html.doc.DocTableWidth;

public class HtmlTableWidth implements TableWidth {
    private final String width;

    public HtmlTableWidth(int width) {
        this.width = String.valueOf(width);
    }

    public HtmlTableWidth(XWPFTable xwpfTable){
        if (xwpfTable.getWidthType() == TableWidthType.PCT){
            this.width = xwpfTable.getWidth() / 50 + "%";
        } else {
            this.width = String.valueOf(xwpfTable.getWidth() / 20);
        }
    }

    public HtmlTableWidth(XWPFTableCell xwpfTableCell){
        String value;
        try {
            if (xwpfTableCell.getCTTc().getTcPr().getGridSpan().getVal().intValue() > 1){
                value = "0";
            } else {
                value = new NonSpanTableCellWidth(xwpfTableCell).width();
            }
        }catch (NullPointerException exception){
            value = new NonSpanTableCellWidth(xwpfTableCell).width();
        }
        this.width = value;
    }

    public HtmlTableWidth(Table table){
        this.width = new DocTableWidth(table).width();
    }

    @Override
    public void applyWidthToElement(Element element) {
        if (!width.equals("0px") && ! width.equals("0")) element.attributes().put("width", width);
    }
}
