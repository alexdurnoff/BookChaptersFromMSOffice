package ru.durnov.chapters;

import org.apache.batik.transcoder.TranscoderException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipOutputStream;

public interface Archive {
    default void compressFiles() throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream(this.archiveUrl());
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)){
            this.images().saveImages(zipOutputStream);
            this.chapters().saveChapters(zipOutputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            throw new IllegalStateException("ParseConfiguration exception " + e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
            throw  new IllegalStateException("Transformer exception " + e.getMessage());
        }
    }

    /**
     * Строка инкапсулирует путь к архиву. Никаких других директорий не создаем. Пишем данные сразу в zip-архив.
     * @return String url.
     */
    String archiveUrl();

    Images images();

    Chapters chapters() throws IOException;

    /**
     * @return String path to created archive;
     */
    default String pathToArchive() throws Exception {
        String url = this.archiveUrl();
        Files.deleteIfExists(Path.of(url));
        compressFiles();
        return url;
    }
}
