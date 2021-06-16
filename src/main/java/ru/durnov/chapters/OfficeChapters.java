package ru.durnov.chapters;

public class OfficeChapters implements Chapters{
    private final ChapterExtractor chapterExtractor;

    public OfficeChapters(ChapterExtractor chapterExtractor) {
        this.chapterExtractor = chapterExtractor;
    }

    @Override
    public ChapterExtractor chapterExtractor() {
        return this.chapterExtractor;
    }
}
