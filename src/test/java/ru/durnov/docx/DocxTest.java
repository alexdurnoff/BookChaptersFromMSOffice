package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;
import ru.durnov.chapters.NonHeaderChapterTitle;
import ru.durnov.debug.DebugWriter;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocxTest {

    //@Test
    void test1() throws Exception {
        Docx docx = new Docx("Test/prikaz1.docx");
        String archiveUrl = docx.archive().pathToArchive();
        new DebugWriter(archiveUrl, ".docx").writeContentToHtml();
    }

    //@Test
    void testDocxDocumentWithOnePictures() throws Exception {
        Docx docx = new Docx("Test/приказ с картинками.docx");
        new DebugWriter(docx.archive().pathToArchive(), ".docx").writeContentToHtml();
    }

    //@Test
    void testThatXWPFDocumentIsValid() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of("Test/prikaz1.docx")));
        System.out.println(xwpfDocument.getParagraphs());
    }

    //@Test
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

    //@Test
    void testSpanCells() throws Exception {
        Docx docx = new Docx("Test/Объединение ячеек.docx");
        new DebugWriter(docx.archive().pathToArchive(), ".docx").writeContentToHtml();
    }


    //@Test
    void testPrikaz1WithLinks() throws Exception {
        Docx docx = new Docx("Test/prikaz1 with links.docx");
        new DebugWriter(docx.archive().pathToArchive(), ".docx").writeContentToHtml();
    }

    //@Test
    void testDefaultMargin() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of("Test/prikaz1 with links.docx")));
        System.out.println(xwpfDocument.getDocument().getBody().getSectPr().getPgMar().getLeft());
    }

    @Test
    void testAllDocxFiles() throws IOException {
        Files.newDirectoryStream(Path.of("Test/")).forEach(path -> {
            String fileName = path.getFileName().toString();
            if (fileName.endsWith(".docx")){
                try {
                    new DebugWriter(
                            new Docx(path.toString())
                            .archive().pathToArchive(),
                            ".docx"
                    ).writeContentToHtml();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Test
    void testDocxFileCreatedByLibreoffice() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(
                Path.of("Test/prikaz1 with links libreoffice.docx"))
        );
        DocxStyleMap docxStyleMap = new DocxStyleMap(xwpfDocument);
        DocxLevel docxLevel = new DocxLevel(docxStyleMap);
        DocxContentChapterChecker checker = new DocxContentChapterChecker();
        xwpfDocument.getParagraphs().forEach(xwpfParagraph -> {
            if (docxStyleMap.paragraphIsHeader(xwpfParagraph)){
                System.out.println("Header is: " + xwpfParagraph.getText());
            } else {
                if (checker.isChapter(xwpfParagraph)){
                    System.out.println("Detected non header start paragraph " + xwpfParagraph.getText());
                }
            }
            try {
                int level = docxLevel.levelByParagraph(xwpfParagraph);
                System.out.println("current level is " + level);
            } catch (Exception ignored) {

            }
        });
    }



}