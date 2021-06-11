package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Chapters;

import java.io.IOException;

public class DocxChapters implements Chapters {
    private final XWPFDocument xwpfDocument;

    public DocxChapters(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
    }

    @Override
    public ChapterExtractor chapterExtractor() {
        return new DocxChapterExtractor(this.xwpfDocument);
    }


}
