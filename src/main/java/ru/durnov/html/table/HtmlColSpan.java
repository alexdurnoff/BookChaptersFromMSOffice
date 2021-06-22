package ru.durnov.html.table;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;

public class HtmlColSpan implements ColSpan {
    private final int span;

    public HtmlColSpan(int span){
        this.span = span;
    }


    public HtmlColSpan(XWPFTableCell xwpfTableCell) {
        int value = 0;
        try {
            value = xwpfTableCell.getCTTc().getTcPr().getGridSpan().getVal().intValue();
        } catch (NullPointerException ignored){

        }
        this.span = value;
    }

    public HtmlColSpan(TableCell cell, TableRow tableRow, Table table) {
        this.span = 1;
    }

    @Override
    public void applyCollSpanToElement(Element element) {
        if (this.span > 1) element.attributes().put("colspan", String.valueOf(span));
    }
}
