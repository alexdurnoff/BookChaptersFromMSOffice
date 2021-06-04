package ru.durnov.doc;

import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Chapters;

public class DocChapters implements Chapters {
    private final String url;

    public DocChapters(String url) {
        this.url = url;
    }

    @Override
    public ChapterExtractor chapterExtractor() {
        return new DocChapterExtractor(this.url);
    }


}
