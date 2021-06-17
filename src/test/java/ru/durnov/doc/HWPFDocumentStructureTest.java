package ru.durnov.doc;

import lombok.SneakyThrows;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.model.StyleSheet;
import org.apache.poi.hwpf.usermodel.*;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
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

    @Test
    void testConvertDocument() throws IOException, ParserConfigurationException, TransformerException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        WordToHtmlConverter converter = new WordToHtmlConverter(document);
        converter.processDocument(hwpfDocument);
        StringWriter stringWriter = new StringWriter();
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer();
        transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
        transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
        transformer.setOutputProperty( OutputKeys.METHOD, "html" );
        transformer.transform(
                new DOMSource( converter.getDocument() ),
                new StreamResult( stringWriter ) );

        String html = stringWriter.toString();
        System.out.println(html);
    }

    @SneakyThrows
    @Test
    void testConvertTable(){
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        Range range = hwpfDocument.getRange();
        Section section = range.getSection(0);
        int numParagraphs = section.numParagraphs();
        int i = 0;
        while (i < numParagraphs){
            Paragraph paragraph = section.getParagraph(i);
            if (paragraph.isInTable()){
                Table table = section.getTable(paragraph);
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                WordToHtmlConverter converter = new WordToHtmlConverter(document);
                converter.processDocumentPart(hwpfDocument,table);
                StringWriter stringWriter = new StringWriter();
                Transformer transformer = TransformerFactory.newInstance()
                        .newTransformer();
                transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
                transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
                transformer.setOutputProperty( OutputKeys.METHOD, "html" );
                transformer.transform(
                        new DOMSource( converter.getDocument() ),
                        new StreamResult( stringWriter ) );

                String html = stringWriter.toString();
                BufferedWriter writer = Files.newBufferedWriter(Path.of("Test/htmlTables/" + "table" + i + ".html"));
                writer.write(html);
                writer.flush();
                i = i + table.numParagraphs();
            } else {
                i++;
            }
        }
    }

    @Test
    void testChapterOutputToHtml() throws IOException, ParserConfigurationException, TransformerException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        DocStyleMap docStyleMap = new DocStyleMap(hwpfDocument);
        Range range = hwpfDocument.getRange();
        Section section = range.getSection(0);
        int numParagraphs = section.numParagraphs();
        int i = 0;
        int start = 0;
        while (i < numParagraphs){
            int end;
            Paragraph paragraph = section.getParagraph(i);
            start += paragraph.text().length();
            if (start >= hwpfDocument.characterLength()) break;
            if (docStyleMap.paragraphIsHeader(paragraph)){
                end = start;
                do {
                    i++;
                    if (i == numParagraphs) break;
                    end += paragraph.text().length();
                    paragraph = section.getParagraph(i);
                }
                while (! docStyleMap.paragraphIsHeader(paragraph));
                System.out.println(start + ":" + end);
                Range newRange = new Range(start, end, hwpfDocument);
                start = end;
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                WordToHtmlConverter converter = new WordToHtmlConverter(document);
                converter.processDocumentPart(hwpfDocument,newRange);
                StringWriter stringWriter = new StringWriter();
                Transformer transformer = TransformerFactory.newInstance()
                        .newTransformer();
                transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
                transformer.setOutputProperty( OutputKeys.ENCODING, "utf-8" );
                transformer.setOutputProperty( OutputKeys.METHOD, "html" );
                transformer.transform(
                        new DOMSource( converter.getDocument() ),
                        new StreamResult( stringWriter ) );

                String html = stringWriter.toString();
                BufferedWriter writer = Files.newBufferedWriter(Path.of("Test/htmlChapters/" + "chapter" + start + ".html"));
                writer.write(html);
                writer.flush();
            } else {
                i++;
            }

        }
    }

    @Test
    void testDocumentTextToString() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        StringBuilder stringBuilder = hwpfDocument.getText();
        Section section = hwpfDocument.getRange().getSection(0);
        int numParagraphs = hwpfDocument.getRange().getSection(0).numParagraphs();
        int fromIndex = 0;
        for (int i = 0; i < numParagraphs; i++){
            Paragraph paragraph = section.getParagraph(i);
            int index = stringBuilder.indexOf(paragraph.text(), fromIndex);
            fromIndex = index + 1;
            System.out.println(index);
        }
    }

}
