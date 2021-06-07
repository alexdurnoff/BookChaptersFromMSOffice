package ru.durnov.chapters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

public interface Archive {
    default void compressFiles() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(this.url());
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)){
            this.images().saveImages(zipOutputStream);
            this.chapters().saveChapters(zipOutputStream);
        }
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
