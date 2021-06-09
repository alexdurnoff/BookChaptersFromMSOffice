package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DocxStyleMapTest {

    private final XWPFDocument xwpfDocument = new XWPFDocument();
    private final XWPFStyles xwpfStyles = xwpfDocument.createStyles();
    private final TestStyleUtils testStyleUtils = new TestStyleUtils(this.xwpfDocument);


    private void addHeaderStyle(){
        this.testStyleUtils.addHeaderStyleWithGeneratedNumber();
    }

    private void addStyleByName(String name){
        this.testStyleUtils.addStyleByNameWithGeneratedNumber(name);
    }

    @Test
    public void testOneStyleDocument() {
        XWPFParagraph paragraph = xwpfDocument.createParagraph();
        addHeaderStyle();
        paragraph.setStyle("Heading 1");
        DocxStyleMap docxStyleMap = new DocxStyleMap(xwpfDocument);
        assertEquals(docxStyleMap.levelByParagraph(paragraph), 1);
    }

    @Test
    void testAddOneHeaderStyleAndOneRegularStyle(){
        XWPFParagraph paragraph1 = xwpfDocument.createParagraph();
        XWPFParagraph paragraph2 = xwpfDocument.createParagraph();
        addStyleByName("Обычный");
        addHeaderStyle();
        paragraph1.setStyle("Heading 2");
        paragraph2.setStyle("Обычный");
        DocxStyleMap docxStyleMap = new DocxStyleMap(xwpfDocument);
        assertEquals(docxStyleMap.levelByParagraph(paragraph1), 1);
        assertThrows(IllegalArgumentException.class, () -> docxStyleMap.levelByParagraph(paragraph2));
    }

    @Test
    void testParagraphIsHeaderForPrikaz1Docx() throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream("Test/prikaz1.docx"));
        DocxStyleMap docxStyleMap = new DocxStyleMap(xwpfDocument);
        xwpfDocument.getBodyElements().forEach(bodyElement -> {
            if (bodyElement instanceof  XWPFParagraph){
                XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
                System.out.println(xwpfParagraph.getStyle());
            }
            System.out.println(docxStyleMap.paragraphIsHeader(bodyElement));
        });
    }

}