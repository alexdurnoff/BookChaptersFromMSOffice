package ru.durnov.docx;


import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;;
import org.junit.jupiter.api.Test;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


class DocxChapterExtractorTest {

    @Test
    void testDocumentStructure() throws IOException, XmlException {
        String url = "Test/prikaz1.docx";
        InputStream inputStream = Files.newInputStream(Path.of(url));
        XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
        List<IBodyElement> bodyElements = xwpfDocument.getBodyElements();
        bodyElements.forEach(bodyElement ->{
            System.out.println(bodyElement.getElementType());
        });
        XWPFStyles styles = xwpfDocument.getStyles();
        int styleNumbers = styles.getNumberOfStyles();
        for (int i = 1; i <= styleNumbers; i++){
            XWPFStyle style = styles.getStyle(String.valueOf(i));
            System.out.println(style.getName());
        }
        XWPFParagraph headerParagraph = null;
        List<XWPFParagraph> paragraphs = xwpfDocument.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            if (paragraph.getText().contains("Общие положения")) headerParagraph = paragraph;
        }
        System.out.println(headerParagraph.getStyleID());
        List<XWPFRun> runs = headerParagraph.getRuns();
        System.out.println(runs.size());
        for (XWPFRun run : runs) {

        }
    }



    @Test
    public void testMammothConverter() throws IOException {
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = converter.convertToHtml(new File("Test/prikaz1.docx"));
        String html = result.getValue(); // The generated HTML
        Set<String> warnings = result.getWarnings(); // Any warnings during conversion
        Files.newBufferedWriter(Path.of("Test/mammothConvertTiHtml.html")).write(html);
    }

}