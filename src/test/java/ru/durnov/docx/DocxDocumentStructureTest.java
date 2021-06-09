package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.xmlbeans.XmlException;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
}
