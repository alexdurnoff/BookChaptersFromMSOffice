package ru.durnov.docx;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

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
        xwpfDocument.getParagraphs().forEach(xwpfParagraph -> {
            xwpfParagraph.getRuns().forEach(xwpfRun -> {
                if (xwpfRun.getEmbeddedPictures().size() > 0){
                    System.out.println("detected pictures");
                    System.out.println(xwpfRun.getPictureText());
                    System.out.println(xwpfRun.text());
                }
            });

        });
    }
}
