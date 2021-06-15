package ru.durnov.html.table;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.jsoup.nodes.Element;

public class HtmlTableVerticalAlignment implements TableVerticalAlignment {
    private final String alignment;

    public HtmlTableVerticalAlignment(XWPFTableCell.XWPFVertAlign verticalAlignment) {
        if (verticalAlignment == XWPFTableCell.XWPFVertAlign.TOP){
            this.alignment = "top";
        } else if (verticalAlignment == XWPFTableCell.XWPFVertAlign.BOTTOM) {
            this.alignment = "bottom";
        } else if (verticalAlignment == XWPFTableCell.XWPFVertAlign.BOTH){
            this.alignment = "baseline";
        } else {
            this.alignment = "middle";
        }
    }

    @Override
    public void applyTableVerticalAlignmentToElement(Element element) {
        element.attributes().put("valign", alignment);
    }
}
