package ru.durnov.html;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.doc.ParagraphWithSection;

public class ParagraphStyleParameters implements HtmlAlignment{
    private final String alignment;
    private final String defaultAlignment = "justify";
    private final int textIndentation;

    public ParagraphStyleParameters(XWPFParagraph xwpfParagraph) {
        if (xwpfParagraph.getAlignment() == ParagraphAlignment.BOTH){
            this.alignment = "justify";
        } else if (xwpfParagraph.getAlignment() == ParagraphAlignment.LEFT){
            this.alignment = "left";
        } else if (xwpfParagraph.getAlignment() == ParagraphAlignment.RIGHT){
            this.alignment = "right";
        } else if (xwpfParagraph.getAlignment() == ParagraphAlignment.CENTER){
            this.alignment = "center";
        } else {
            this.alignment = defaultAlignment;
        }
        if (xwpfParagraph.getFirstLineIndent() != -1) {
            this.textIndentation = xwpfParagraph.getFirstLineIndent()/20;
        } else {
            this.textIndentation = 40;
        }

    }

    public ParagraphStyleParameters(Paragraph paragraph){
        int align = paragraph.getFontAlignment();
        if (align == 3) {
            this.alignment = "justify";
        } else if (align == 0) {
            this.alignment = "left";
        } else if (align == 2) {
            this.alignment = "right";
        } else if (align == 1) {
            this.alignment = "center";
        } else {
            this.alignment = defaultAlignment;
        }
        if (paragraph.getFirstLineIndent() != -1){
            this.textIndentation = paragraph.getFirstLineIndent()/20;
        } else {
            this.textIndentation = 40;
        }
    }



    @Override
    public void applyToParagraphElement(Element element) {
        element.attributes().put("style", "text-align:" + this.alignment + ";hyphenate:auto");
    }

    public String value() {
        return this.alignment;
    }
}
