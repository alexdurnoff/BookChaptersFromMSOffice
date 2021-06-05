package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.util.List;

public class DocxContentChapter implements Chapter {
    private final int level;


    public DocxContentChapter(Level level, Index index, List<XWPFParagraph> paragraphs) {
        this.level = level.currentLevel();
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public int level() {
        return this.level;
    }

    @Override
    public boolean inline() {
        return true;
    }

    @Override
    public String content() {
        return null;
    }

    @Override
    public void saveToArchive() {

    }
}
