package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocxContentChapterChecker {
    private final static Pattern pattern = Pattern.compile("^\\s*([0-9])\\s*(.)+$");
    private final Matcher matcher;

    public DocxContentChapterChecker(XWPFParagraph xwpfParagraph) {
        this.matcher = pattern.matcher(xwpfParagraph.getText().split("\n")[0]);
    }

    public boolean isChapter() {
        return matcher.matches();
    }
}
