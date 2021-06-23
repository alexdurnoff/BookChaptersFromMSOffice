package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import ru.durnov.debug.DebugWriter;
import ru.durnov.docx.Docx;
import ru.durnov.docx.DocxStyleMap;

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

    @Test
    void testGost() throws Exception {
        Doc doc = new Doc("Test/_ГОСТ 6134-2007.doc");
        String archiveUrl = doc.archive().pathToArchive();
        new DebugWriter(archiveUrl, ".doc").writeContentToHtml();
    }

    @Test
    void testPrikaz1() throws Exception {
        Doc doc = new Doc("Test/prikaz1.doc");
        String archiveUrl = doc.archive().pathToArchive();
        new DebugWriter(archiveUrl, ".doc").writeContentToHtml();
    }

    @Test
    void testWithPictures() throws Exception {
        Doc doc = new Doc("Test/prikaz1 with pictures.doc");
        String archiveUrl = doc.archive().pathToArchive();
        new DebugWriter(archiveUrl, ".doc").writeContentToHtml();
    }
}
