package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocxContentChapterChecker {
    private final static Pattern pattern = Pattern.compile("^((\\s*[0-9]+(.)*)+\\s*[а-яА-ЯA-Za-z]+)");


    public boolean isChapter(IBodyElement bodyElement) {
        if (! (bodyElement instanceof XWPFParagraph)) return false;
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
        String text = xwpfParagraph.getText();
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            String startString = matcher.group();
            return text.startsWith(startString);
        }
        return false;
    }
}
