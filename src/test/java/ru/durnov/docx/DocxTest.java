package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.xwpf.converter.IURIResolver;
import org.apache.poi.xwpf.converter.IXWPFConverter;
import org.apache.poi.xwpf.converter.internal.xhtml.XHTMLMapper;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.converter.xhtml.XWPF2XHTMLConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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



}