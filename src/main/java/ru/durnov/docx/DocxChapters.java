package ru.durnov.docx;

import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Chapters;

import java.io.IOException;

public class DocxChapters implements Chapters {
    private final String url;

    public DocxChapters(String url) {
        this.url = url;
    }

    @Override
    public ChapterExtractor chapterExtractor() {
        return new DocxChapterExtractor(this.url);
    }


}
