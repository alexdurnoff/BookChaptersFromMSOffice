package ru.durnov.chapters;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public interface Chapters {
    ChapterExtractor chapterExtractor();
    default void saveChapters(ZipOutputStream zipOutputStream) throws IOException,
            ParserConfigurationException,
            TransformerException {
        ChapterExtractor chapterExtractor = this.chapterExtractor();
        List<Chapter> chapterList = chapterExtractor.chapterList();
        zipOutputStream.putNextEntry(new ZipEntry("chapters.json"));
        new ObjectMapper().writeValue(zipOutputStream, chapterList);
    }
}
