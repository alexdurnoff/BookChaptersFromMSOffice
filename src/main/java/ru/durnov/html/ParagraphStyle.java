package ru.durnov.html;

import org.apache.poi.wp.usermodel.CharacterRun;
import org.apache.poi.wp.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;


public interface ParagraphStyle {
    void applyToParagraphElement(Element element);
}
