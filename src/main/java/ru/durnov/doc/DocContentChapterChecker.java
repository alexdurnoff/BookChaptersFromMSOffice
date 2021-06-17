package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocContentChapterChecker {
    private final static Pattern pattern = Pattern.compile("^\\s*([0-9])\\s*(.)+$");

    public boolean isChapter(Paragraph paragraph) {
        if (paragraph.isInTable()) return false;
        Matcher matcher = pattern.matcher(paragraph.text().split("\n")[0]);
        return matcher.matches();
    }
}