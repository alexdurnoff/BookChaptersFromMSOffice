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
        return this.xwpfParagraph.getText();
    }

    @Override
    public int level() {
        return this.level;
    }

    @Override
    public boolean inline() {
        return false;
    }

    @Override
    public String content() {
        return "";
    }

}
