package ru.durnov.debug;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DebugJsonString {

    private final String url;

    public DebugJsonString(String url) {
        if (! url.endsWith(".zip")) throw new IllegalArgumentException(
                "The URL must be ends with .zip"
        );
        this.url = url;
    }

    public String json() throws IOException {
        String json = "";
        try(ZipInputStream zipInputStream = new ZipInputStream(
                Files.newInputStream(
                        Path.of(url)
                )
        )){
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            assert zipEntry != null;
            while (!zipEntry.getName().equals("chapters.json")) zipEntry = zipInputStream.getNextEntry();
            json = new String(zipInputStream.readAllBytes());
        }

        return json;
    }
}
