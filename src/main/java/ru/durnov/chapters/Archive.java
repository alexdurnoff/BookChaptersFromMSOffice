package ru.durnov.chapters;

import java.io.IOException;

public interface Archive {
    
    default void compressFiles() throws IOException {
        this.images().saveImages(url());
        this.chapters().saveChapters(url());
    }

    /**
     * Строка инкапсулирует путь к архиву. Никаких других директорий не создаем. Пишем данные сразу в zip-архив.
     * @return String url.
     */
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
