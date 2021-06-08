package ru.durnov.html;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;

public class WordHtmlParagraphAlign implements HtmlAlignment{
    private final String alignment;
    private final String defaultAlignment = "justify";

    public WordHtmlParagraphAlign(XWPFParagraph xwpfParagraph) {
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
    }

    public WordHtmlParagraphAlign(Paragraph paragraph){
        if (paragraph.getJustification() == 3) {
            this.alignment = "justify";
        } else if (paragraph.getJustification() == 1) {
            this.alignment = "center";
        } else if (paragraph.getJustification() == 2){
            this.alignment = "right";
        } else if (paragraph.getJustification() == 0){
            this.alignment = "left";
        } else {
            this.alignment = defaultAlignment;
        }
    }

    @Override
    public void applyToElement(Element element) {
        element.attributes().put("align", alignment);
    }
}
