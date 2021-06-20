package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocxTest {

    @Test
    void test1() throws Exception {
        Docx docx = new Docx("Test/prikaz1.docx");
        String archiveUrl = docx.archive().pathToArchive();
    }

    @Test
    void testDocxDocumentWithOnePictures() throws Exception {
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
    void testSpanCells() throws Exception {
        Docx docx = new Docx("Test/Объединение ячеек.docx");
        docx.archive().pathToArchive();
    }


    @Test
    void testPrikaz1WithLinks() throws Exception {
        Docx docx = new Docx("Test/prikaz1 with links.docx");
        System.out.println(docx.archive().pathToArchive());
    }

    @Test
    void testDefaultMargin() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of("Test/prikaz1 with links.docx")));
        System.out.println(xwpfDocument.getDocument().getBody().getSectPr().getPgMar().getLeft());
    }



}