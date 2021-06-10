package ru.durnov.chapters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public interface Chapters {
    ChapterExtractor chapterExtractor();
    default void saveChapters(ZipOutputStream zipOutputStream) throws IOException {
        List<Chapter> chapterList = this.chapterExtractor().chapterList();
        zipOutputStream.putNextEntry(new ZipEntry("chapters.json"));
        new ObjectMapper().writeValue(zipOutputStream, chapterList);
        //Дебажим чтобы посмотреть.
        for (int i = 0; i <chapterList.size(); i++){
            Path path = Path.of("Test/viewHtml/" + (i+1) + ".html");
            BufferedWriter bufferedWriter = Files.newBufferedWriter(path);
            bufferedWriter.write(chapterList.get(i).content());
            bufferedWriter.flush();
        }
    }
}
