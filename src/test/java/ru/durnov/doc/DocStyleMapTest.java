package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DocStyleMapTest {

    @Test
    void testThat18ParagraphIsHeader() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(Files.newInputStream(Path.of("Test/prikaz1.doc")));
        DocStyleMap docStyleMap = new DocStyleMap(hwpfDocument);
        Paragraph paragraph = hwpfDocument.getRange().getParagraph(18);
        assertTrue(docStyleMap.paragraphIsHeader(paragraph));
    }

}