package ru.durnov.docx;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxDocumentStructureTest {

    @Test
    void test1() throws IOException, XmlException {
        XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream("Test/prikaz1.docx"));
        CTStyles style = xwpfDocument.getStyle();
        style.getStyleList().forEach(ctStyle -> {
            System.out.println(ctStyle.getName().getVal());
            System.out.println(ctStyle.getStyleId());
        });
        XWPFStyles xwpfStyles = xwpfDocument.getStyles();
        System.out.println(xwpfStyles.getStyle("2").getName());

    }

    @Test
    void testFindPageSize() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream("Test/prikaz1.docx"));
        CTBody body = xwpfDocument.getDocument().getBody();
        System.out.println(body.isSetSectPr());
        CTSectPr sectPr = body.getSectPr();
        CTPageSz pgSz = sectPr.getPgSz();
        System.out.println(pgSz.getW().intValue());
        System.out.println(pgSz.getH());
    }

    @Test
    void testMammoth() throws IOException {
        DocumentConverter converter = new DocumentConverter();
        Result<String> result = converter.convertToHtml(new File("Test/prikaz1.docx"));
        String html = result.getValue(); // The generated HTML
        Files.newBufferedWriter(Path.of("Test/mammoth.html")).write(html);
    }

    @Test
    void testDocumentWithPictures() throws IOException, OpenXML4JException {
        XWPFDocument xwpfDocument = new XWPFDocument(
                Files.newInputStream(
                        Path.of("Test/приказ с картинками.docx")
                )
        );
        xwpfDocument.getAllPackagePictures().forEach(xwpfPictureData -> {
            System.out.println(xwpfPictureData.getPictureType());
            System.out.println(xwpfPictureData.getFileName());
        });
    }
}
