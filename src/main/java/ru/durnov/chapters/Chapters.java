package ru.durnov.chapters;

import java.io.IOException;

public interface Chapters {
    ChapterExtractor chapterExtractor();
    default void saveChapters() throws IOException {
        this.chapterExtractor().chapterList().forEach(Chapter::saveToArchive);
    };
}
