package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HWPFDocumentStructureTest {

    @Test
    void test1() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        StyleSheet styleSheet = hwpfDocument.getStyleSheet();
        Range range = hwpfDocument.getRange();
        int numberOfSections = range.numSections();
        System.out.println(numberOfSections);
        for (int i = 0; i < numberOfSections; i++){
            Section section = range.getSection(i);
            int numberOfParagraphs = section.numParagraphs();
            for (int j = 0; j < numberOfParagraphs; j++){
                Paragraph paragraph = section.getParagraph(j);
                paragraph.getStyleIndex();
                String styleName = styleSheet.getStyleDescription(paragraph.getStyleIndex()).getName();
                System.out.println(styleName);
            }

        }
    }
}
