package ru.durnov.chapters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public interface Chapters {
    ChapterExtractor chapterExtractor();
    default void saveChapters(ZipOutputStream zipOutputStream) throws IOException {
        this.chapterExtractor().chapterList().forEach(chapter -> {
            try {
                Files.newBufferedWriter(Path.of("Test/viewHtml/" + chapter.title() + ".html")).write(chapter.content());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        zipOutputStream.putNextEntry(new ZipEntry("chapters.json"));
        new ObjectMapper().writeValue(zipOutputStream, this.chapterExtractor().chapterList());
    }
}
