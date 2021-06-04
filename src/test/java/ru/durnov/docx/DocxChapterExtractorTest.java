package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocxChapterExtractorTest {

    @Test
    void testDocumentStructure() throws IOException {
        String url = "Test/prikaz1.docx";
        InputStream inputStream = Files.newInputStream(Path.of(url));
        XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        System.out.println(paragraphs.size());
        paragraphs.forEach(xwpfParagraph -> {
            System.out.println("new Paragraph:");
            System.out.println(xwpfParagraph.getText());
            xwpfParagraph.getRuns().forEach(xwpfRun -> {
                System.out.println("new XWPFRun:");
                System.out.println(xwpfRun.getText(0));
            });
        });
    }

}