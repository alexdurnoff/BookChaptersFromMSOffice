package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;

public class HtmlRowSpan implements RowSpan {
    private final int span;

    public HtmlRowSpan(int span){
        this.span = span;
    }

    public HtmlRowSpan(XWPFTableCell xwpfTableCell) {
        int value = 0;
        try {
            value = xwpfTableCell.getCTTc().getTcPr().getVMerge().getVal().intValue() + 1;
        } catch (NullPointerException ignored){

        }
        this.span = value;
    }

    @Override
    public void applyRowSpanToElement(Element element) {
        if (this.span > 0) element.attributes().put("rowspan", String.valueOf(span));
    }
}
