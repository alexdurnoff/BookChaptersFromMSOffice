package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Chapters;
import ru.durnov.oldword.OldContentChapterExtractor;

public class DocChapters implements Chapters {
    private final HWPFDocument hwpfDocument;
    private final ChapterExtractor chapterExtractor;


    public DocChapters(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
        this.chapterExtractor = new OldContentChapterExtractor(hwpfDocument);
    }


    @Override
    public ChapterExtractor chapterExtractor() {
        return this.chapterExtractor;
    }

}
