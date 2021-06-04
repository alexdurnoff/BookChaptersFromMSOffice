package ru.durnov.doc;

import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;

import java.util.List;

public class DocChapterExtractor implements ChapterExtractor {
    private final String url;

    public DocChapterExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Chapter> chapterList() {
        return null;
    }
}
