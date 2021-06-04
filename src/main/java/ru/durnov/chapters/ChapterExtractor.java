package ru.durnov.chapters;

import java.io.IOException;
import java.util.List;

public interface ChapterExtractor {
    List<Chapter> chapterList() throws IOException;
}
