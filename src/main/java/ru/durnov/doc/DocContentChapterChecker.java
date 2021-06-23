package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocContentChapterChecker {
    private final static Pattern pattern = Pattern.compile("^\\s*([0-9])\\s*(.)*");

    public boolean isChapter(Range range) {
        if (! (range instanceof Paragraph)) return false;
        Paragraph paragraph = (Paragraph) range;
        if (paragraph.isInTable()) return false;
        if (paragraph.isInList()) return false;
        Matcher matcher = pattern.matcher(paragraph.text());
        if (matcher.find()){
            String startString = matcher.group(1);
            return paragraph.text().startsWith(startString);
        }
        return false;
    }
}
