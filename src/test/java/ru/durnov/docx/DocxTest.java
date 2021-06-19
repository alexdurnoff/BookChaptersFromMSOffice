package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.xwpf.converter.IURIResolver;
import org.apache.poi.xwpf.converter.IXWPFConverter;
import org.apache.poi.xwpf.converter.internal.xhtml.XHTMLMapper;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.converter.xhtml.XWPF2XHTMLConverter;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.MSOffice;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.microsoft.MSOwnerFileParser;
import org.apache.tika.parser.microsoft.WordExtractor;
import org.apache.tika.sax.ToXMLContentHandler;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.html.AbstractHtmlExporter;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocxTest {

    @Test
    void test1() throws IOException, TranscoderException {
        Docx docx = new Docx("Test/prikaz1.docx");
        String archiveUrl = docx.archive().pathToArchive();
    }

    @Test
    void testDocxDocumentWithOnePictures() throws IOException, TranscoderException {
        Docx docx = new Docx("Test/приказ с картинками.docx");
        docx.archive().pathToArchive();
    }

    @Test
    void testThatXWPFDocumentIsValid() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of("Test/prikaz1.docx")));
        System.out.println(xwpfDocument.getParagraphs());
    }

    @Test
    void testRowSpan1Structure() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of("Test/Объединение ячеек.docx")));
        XWPFTable xwpfTable = xwpfDocument.getTables().get(0);
        List<XWPFTableRow> rows = xwpfTable.getRows();
        for (XWPFTableRow row : rows) {
            List<XWPFTableCell> tableCells = row.getTableCells();
            for (XWPFTableCell tableCell : tableCells) {
                System.out.println(tableCell.getText());
                try {
                    System.out.println(tableCell.getCTTc().getTcPr().getVMerge().getVal().intValue());
                } catch (NullPointerException e) {
                    System.out.println("null pointer exception is detected");
                }
            }
        }
    }

    @Test
    void testSpanCells() throws IOException, TranscoderException {
        Docx docx = new Docx("Test/Объединение ячеек.docx");
        docx.archive().pathToArchive();
    }

    @Test
    void testConverter() throws Exception {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of("Test/Объединение ячеек.docx")));
        IXWPFConverter<XHTMLOptions> converter = XWPF2XHTMLConverter.getInstance();
        converter.convert(
                xwpfDocument,
                Files.newOutputStream(Path.of("Test/convert.html")),
                new XHTMLOptions()
        );
    }

    @Test
    void apacheTikaTest() throws IOException, TikaException, SAXException {
        InputStream inputStream = Files.newInputStream(Path.of("Test/prikaz1.docx"));
        Parser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        ToXMLContentHandler toXMLContentHandler = new ToXMLContentHandler();
        ParseContext parseContext = new ParseContext();
        parser.parse(inputStream, toXMLContentHandler, metadata, parseContext);
        String html = toXMLContentHandler.toString();
        System.out.println(html);
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("Test/tikaTest.html"));
        bufferedWriter.write(html);
        bufferedWriter.flush();
    }

    @Test
    void testDocx4jConverting() throws IOException, Docx4JException {
        WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(
                Files.newInputStream(Path.of("Test/prikaz1.docx"))
        );
        OutputStream outputStream = Files.newOutputStream(Path.of("Test/testDocx4j.html"));
        String imageDir = "Test/prikaz1.docx_files";
        String imageTargetUri = "parikaz1.docx";
        Docx4J.toHTML(mlPackage, imageDir, imageTargetUri, outputStream);
    }

    @Test
    void testPrikaz1WithLinks() throws IOException, TranscoderException {
        Docx docx = new Docx("Test/prikaz1 with links.docx");
        System.out.println(docx.archive().pathToArchive());
    }



}