package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Chapters;

public class DocChapters implements Chapters {
    private final HWPFDocument hwpfDocument;

    public DocChapters(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
    }


    @Override
    public ChapterExtractor chapterExtractor() {
        return new DocChapterExtractor(this.hwpfDocument);
    }


}
