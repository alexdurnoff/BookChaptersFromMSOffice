package ru.durnov.chapters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public interface Chapters {
    ChapterExtractor chapterExtractor();
    default void saveChapters(ZipOutputStream zipOutputStream) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry("chapters.json"));
        new ObjectMapper().writeValue(zipOutputStream, this.chapterExtractor().chapterList());
    }
}
