package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocxParagraphLevelComparatorTest {
    private final XWPFDocument xwpfDocument = new XWPFDocument();

    @Test
    public void testCompare2ParagraphWithEqualsLevels(){
        XWPFParagraph xwpfParagraph1 = this.xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph1.createRun();
        xwpfRun.setText("2.1.3 Общие положения");
        XWPFParagraph xwpfParagraph2 = this.xwpfDocument.createParagraph();
        xwpfRun = xwpfParagraph2.createRun();
        xwpfRun.setText("2.3.3. Думы о былом");
        assertEquals(0,
                new DocxParagraphLevelComparator()
                        .compare(xwpfParagraph1,
                                xwpfParagraph2));
    }

    @Test
    public void testCompare2ParagraphWithDefferentLevels(){
        XWPFParagraph xwpfParagraph1 = this.xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph1.createRun();
        xwpfRun.setText("2.1.3 Общие положения");
        XWPFParagraph xwpfParagraph2 = this.xwpfDocument.createParagraph();
        xwpfRun = xwpfParagraph2.createRun();
        xwpfRun.setText("2.3.3.4 Думы о былом");
        assertEquals(-1,
                new DocxParagraphLevelComparator()
                        .compare(xwpfParagraph1,
                                xwpfParagraph2));
    }

    @Test
    public void testCompare2ParagraphWithDefferentLevels2(){
        XWPFParagraph xwpfParagraph1 = this.xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph1.createRun();
        xwpfRun.setText("2.1.3 Общие положения");
        XWPFParagraph xwpfParagraph2 = this.xwpfDocument.createParagraph();
        xwpfRun = xwpfParagraph2.createRun();
        xwpfRun.setText("2.3. Думы о былом");
        assertEquals(1,
                new DocxParagraphLevelComparator()
                        .compare(xwpfParagraph1,
                                xwpfParagraph2));
    }

}