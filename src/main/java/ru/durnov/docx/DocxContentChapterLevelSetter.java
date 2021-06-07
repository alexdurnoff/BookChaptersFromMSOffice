package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.chapters.Level;

public class DocxContentChapterLevelSetter {
    private final Level level;
    private final XWPFParagraph xwpfParagraph;

    public DocxContentChapterLevelSetter(Level level, XWPFParagraph xwpfParagraph) {
        this.level = level;
        this.xwpfParagraph = xwpfParagraph;
    }

    public void setLevelValue() {

    }
}
