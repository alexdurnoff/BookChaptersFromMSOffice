package ru.durnov.chapters;

import java.io.IOException;

public interface Archive {
    
    default void compressFiles() throws IOException {
        this.images().saveImages();
        this.chapters().saveChapters();
    };

    String url();

    Images images();

    Chapters chapters();

    /**
     * @return String path to created archive;
     */
    default String pathToArchive() throws IOException {
        compressFiles();
        return url();
    }
}
