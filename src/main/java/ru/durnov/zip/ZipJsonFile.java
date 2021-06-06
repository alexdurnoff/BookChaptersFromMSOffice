package ru.durnov.zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipJsonFile {
    private final String url;

    public ZipJsonFile(String url) {
        this.url = url;
    }

    public void saveToArchive() {
        Path jsonFile = Path.of(url + "/chapters.json");
        try (FileInputStream fileInputStream = new FileInputStream(jsonFile.toFile());
             FileOutputStream fileOutputStream = new FileOutputStream(url + "/archive.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)){
            ZipEntry zipEntry = new ZipEntry("chapters.json");
            zipOutputStream.putNextEntry(zipEntry);
            byte[] buffer = new byte[1000000];
            int length = fileInputStream.read(buffer);
            while (length > 0) {
                zipOutputStream.write(buffer, 0, length);
                length = fileInputStream.read(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
