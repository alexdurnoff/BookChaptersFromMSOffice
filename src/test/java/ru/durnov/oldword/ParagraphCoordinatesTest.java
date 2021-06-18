package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.jupiter.api.Test;
import ru.durnov.doc.ParagraphList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphCoordinatesTest {

    @Test
    void testFirstParagraphCoordinates() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        Paragraph paragraph = hwpfDocument.getRange().getParagraph(0);
        ParagraphCoordinates paragraphCoordinates = new ParagraphCoordinates(
                hwpfDocument,
                paragraph ,
                0
        );
        assertEquals(paragraphCoordinates.start(), 0);
        assertEquals(paragraphCoordinates.stop(), 161);
    }

    @Test
    void testSecondaryConstructorWithFirstParagraph() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(
                Files.newInputStream(Path.of("Test/prikaz1.doc"))
        );
        List<Paragraph> paragraphList = new ParagraphList(hwpfDocument).list();
        Paragraph paragraph = paragraphList.get(0);
        ParagraphCoordinates paragraphCoordinates = new ParagraphCoordinates(
                hwpfDocument,
                paragraphList,
                paragraph
        );
        assertEquals(paragraphCoordinates.start(), 0);
        assertEquals(paragraphCoordinates.stop(), 161);
    }

}