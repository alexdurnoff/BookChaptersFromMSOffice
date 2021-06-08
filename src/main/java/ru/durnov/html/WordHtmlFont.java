package ru.durnov.html;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.HtmlFont;

public class WordHtmlFont implements HtmlFont {
    private final String fontName;
    private final String fontSize;
    private final String fontFamily;

    public WordHtmlFont(XWPFRun xwpfRun) {
        this.fontName = xwpfRun.getFontName();
        this.fontSize = String.valueOf(xwpfRun.getFontSize());
        this.fontFamily = xwpfRun.getFontFamily();
    }

    public WordHtmlFont(CharacterRun characterRun){
        this.fontName = characterRun.getFontName();
        this.fontSize = String.valueOf(characterRun.getFontSize());
        this.fontFamily = "";//Пока не будем использовать
    }

    @Override
    public void applyToElement(Element element) {

    }
}
