package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.junit.jupiter.api.Test;
import ru.durnov.chapters.Index;
import ru.durnov.doc.DocContentChapterChecker;
import ru.durnov.doc.DocStyleMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class OldChapterCoordinatesTest {

    @Test
    void testCoordinatesFirstParagraph() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(Files.newInputStream(Path.of("Test/prikaz1.doc")));
        OldChapterCoordinates coordinates = new OldChapterCoordinates(
                hwpfDocument,
                new Index(),
                new DocStyleMap(hwpfDocument),
                new DocContentChapterChecker()
        );
        assertEquals(0, coordinates.start());
        assertEquals(901, coordinates.stop());
        System.out.println(hwpfDocument.getText().substring(coordinates.start(), coordinates.stop()));
    }

}