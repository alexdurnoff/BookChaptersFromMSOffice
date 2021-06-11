package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DocxTest {

    @Test
    void test1() throws IOException {
        Docx docx = new Docx("Test/prikaz1.docx");
        String archiveUrl = docx.archive().pathToArchive();
    }

    @Test
    void testDocxDocumentWithOnePictures() throws IOException {
        Docx docx = new Docx("Test/приказ с картинками.docx");
        docx.archive().pathToArchive();
    }

    @Test
    void testThatXWPFDocumentIsValid() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of("Test/prikaz1.docx")));
        System.out.println(xwpfDocument.getParagraphs());
    }



}