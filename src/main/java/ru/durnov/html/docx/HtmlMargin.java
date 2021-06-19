package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;

public class HtmlMargin {
    private final int margin;


    public HtmlMargin(XWPFParagraph xwpfParagraph, CTPageMar ctPageMar) {
        int value = xwpfParagraph.getFirstLineIndent();
        if (value == -1) {
            if (ctPageMar != null){
                value = ctPageMar.getLeft().intValue()/20;
            }
        }
        this.margin = value;
    }

    public HtmlMargin(CTPageMar ctPageMar){
        int value = -1;
        if (ctPageMar != null){
            value = ctPageMar.getLeft().intValue()/20;
        }
        this.margin = value;
    }

    public void applyTo(Element div) {
        if (this.margin != -1) {
            div.attributes().put("style", "margin:" + this.margin);
        }
    }
}
