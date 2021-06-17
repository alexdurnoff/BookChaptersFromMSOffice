package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HWPFDocumentStructureTest {

    @Test
    void test1() throws IOException, ParserConfigurationException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        StyleSheet styleSheet = hwpfDocument.getStyleSheet();
        Range range = hwpfDocument.getRange();
        int numberOfSections = range.numSections();
        for (int i = 0; i < numberOfSections; i++){
            Section section = range.getSection(i);
            int numberOfParagraphs = section.numParagraphs();
            for (int j = 0; j < numberOfParagraphs; j++){
                Paragraph paragraph = section.getParagraph(j);
                if (paragraph.isInTable()){
                    try {
                        Table table = section.getTable(paragraph);
                        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                        WordToHtmlConverter converter = new WordToHtmlConverter(document);
                        converter.processDocumentPart(hwpfDocument, table);
                        System.out.println(document);
                    } catch (IllegalArgumentException e) {

                    }
                }
            }
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            WordToHtmlConverter converter = new WordToHtmlConverter(document);
            converter.processDocument(hwpfDocument);
            System.out.println(document);
        }
    }
}
