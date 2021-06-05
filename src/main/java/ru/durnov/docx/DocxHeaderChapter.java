package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.chapters.Chapter;

import java.util.ArrayList;
import java.util.List;

public class DocxHeaderChapter implements Chapter {
    private final Integer level;
    private final XWPFParagraph xwpfParagraph;

    public DocxHeaderChapter(Integer level, XWPFParagraph xwpfParagraph) {
        this.level = level;
        this.xwpfParagraph = xwpfParagraph;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public int level() {
        return 0;
    }

    @Override
    public boolean inline() {
        return false;
    }

    @Override
    public String content() {
        return null;
    }

    @Override
    public void saveToArchive() {

    }
}
