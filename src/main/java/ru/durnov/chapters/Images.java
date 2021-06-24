package ru.durnov.chapters;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public interface Images {
    ImageExtractor imageExtractor();
    default void saveImages(ZipOutputStream zipOutputStream) throws IOException, TranscoderException {
        zipOutputStream.putNextEntry(new ZipEntry("library/"));
        zipOutputStream.closeEntry();
        for (Image image : this.imageExtractor().imageList()) {
            zipOutputStream.putNextEntry(new ZipEntry("library/" + image.name()));
            byte[] bytes = image.asByteArray();
            zipOutputStream.write(bytes, 0, bytes.length);
            zipOutputStream.closeEntry();
        }
    }


}
