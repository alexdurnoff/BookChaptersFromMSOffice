package ru.durnov.debug;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonChapters {

    private final List<JsonChapter> chapters;

    public JsonChapters(String json) throws JsonProcessingException {
        JsonChapter[] chapters = new ObjectMapper()
                .readerForArrayOf(JsonChapter.class)
                .readValue(json);
        this.chapters = new ArrayList<>(Arrays.asList(chapters));
    }

    public void writeChaptersToHtml(Path outputDirectory) throws IOException {
        int count = chapters.size();
        for (int i = 0; i < count; i++) {
            JsonChapter jsonChapter = chapters.get(i);
            jsonChapter.writeContentToHtml(outputDirectory, i);
        }
    }

    public void writeLevelMap(Path outputDirectory) throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(
                Path.of(outputDirectory.toString() + "/" + "map")
        );
        this.chapters.forEach(jsonChapter -> {
            try {
                bufferedWriter.write(jsonChapter.getTitle()
                        + ":" + jsonChapter.getLevel()
                        + ":" + jsonChapter.isInline());
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.flush();
    }
}
