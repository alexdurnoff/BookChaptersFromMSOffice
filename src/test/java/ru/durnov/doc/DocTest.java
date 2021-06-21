package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.hwpf.HWPFDocument;
import org.junit.jupiter.api.Test;
import ru.durnov.debug.DebugWriter;
import ru.durnov.docx.Docx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocTest {

    @Test
    void testAllDocxFiles() throws IOException {
        Files.newDirectoryStream(Path.of("Test/")).forEach(path -> {
            String fileName = path.getFileName().toString();
            if (fileName.endsWith(".doc")){
                try {
                    new DebugWriter(
                            new Doc(path.toString())
                                    .archive().pathToArchive(),
                            ".doc"
                    ).writeContentToHtml();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
