package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HWPFDocumentStructureTest {

    @Test
    void test1() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        Range range = hwpfDocument.getRange();
        int length = range.numCharacterRuns();
        int numParagraphs = range.numParagraphs();
        for (int i = 0; i < numParagraphs; i++){
            Paragraph paragraph = range.getParagraph(i);
            System.out.println(paragraph.text() + ":" + paragraph.getJustification());
            int i1 = paragraph.numCharacterRuns();
            for (int j =0; j < i1; j++){
                System.out.println(paragraph.getCharacterRun(j).text() + ":" + paragraph.getJustification());
            }

        }
    }
}
