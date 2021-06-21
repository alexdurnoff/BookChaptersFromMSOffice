package ru.durnov.debug;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonChapter {
    private String title;
    private String content;
    private boolean inline;
    private int level;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void writeContentToHtml(Path outputDirectory, int number) throws IOException {
        String pathToFile = outputDirectory.toString() + "/" + (number + 1) + ".html";
        Path outputPath = Path.of(pathToFile);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(outputPath);
        bufferedWriter.write(content);
        bufferedWriter.flush();

    }
}
