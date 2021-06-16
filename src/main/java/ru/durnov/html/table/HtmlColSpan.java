package ru.durnov.html.table;

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
    @Override
    public void applyCollSpanToElement(Element element) {
        if (this.span > 0) element.attributes().put("colspan", String.valueOf(span));
    }
}
