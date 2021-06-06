package ru.durnov.chapters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public interface Chapters {
    ChapterExtractor chapterExtractor();
    default void saveChapters(String url) {
        try(OutputStream outputStream = Files.newOutputStream(Path.of(url), StandardOpenOption.APPEND);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            zipOutputStream.putNextEntry(new ZipEntry("chapters.json"));
            List<Chapter> chapterList = this.chapterExtractor().chapterList();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(zipOutputStream, chapterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
