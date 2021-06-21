package ru.durnov.debug;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class DebugWriter {
    private final Path outputDirectory;
    private final String json;

    public DebugWriter(String url, String extension) throws IOException {
        if (! url.endsWith(".zip")) throw new IllegalArgumentException(
                "The URL must be ends with .zip"
        );
        String outputDirectoryName = "/tmp/html_" +
                Path.of(url)
                        .getFileName()
                        .toString()
                        .replace(".zip", extension);
        this.outputDirectory = Path.of(outputDirectoryName);
        this.json = new DebugJsonString(url).json();
    }

    public void writeContentToHtml() throws IOException {
        if (Files.notExists(outputDirectory)) Files.createDirectory(outputDirectory);
        new JsonChapters(json).writeChaptersToHtml(outputDirectory);
    }
}
