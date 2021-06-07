package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DotNumbersInHeaderTest {
    private final XWPFDocument xwpfDocument = new XWPFDocument();

    @Test
    public void testParagraphWith2Dots(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("2.1.3 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertEquals(2, dotNumbersInHeader.levelDepth());
    }

    @Test
    void testParagraphWithoutDots(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("2 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertEquals(0, dotNumbersInHeader.levelDepth());
    }

    @Test
    void testParagraphWith2DotsInBodyAndDotAtEnd(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("2.1.3 Общие положения.");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertEquals(2, dotNumbersInHeader.levelDepth());
    }

    @Test
    void testParagraphWithSpaceInStartCharacter(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(" 2.1.3 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertEquals(2, dotNumbersInHeader.levelDepth());
    }

    @Test
    void testParagraphWithSpaceAfterDot(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("2. 1.3 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertEquals(2, dotNumbersInHeader.levelDepth());
    }

    @Test
    void testParagraphWithSpaceBeforeSecondDot(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("2.1 .3 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertEquals(2, dotNumbersInHeader.levelDepth());
    }

    @Test
    void testParagraphWithOutNumbersInFirstRun(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertThrows(IllegalArgumentException.class, dotNumbersInHeader::levelDepth);
    }

    @Test
    void testParagraphWithLetterBeforeNumber(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("А2.1.3 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertThrows(IllegalArgumentException.class, dotNumbersInHeader::levelDepth);
    }

    @Test
    void testIfHeaderPlacedInSecondRunThenThrowIllegalArgumentException(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("Hello");
        xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("2.1.3 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertThrows(IllegalArgumentException.class, dotNumbersInHeader::levelDepth);
    }

    @Test
    void testParagraphWithThreeDot(){
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("2.1 .3.4 Общие положения");
        DotNumbersInHeader dotNumbersInHeader = new DotNumbersInHeader(xwpfParagraph);
        assertEquals(3, dotNumbersInHeader.levelDepth());
    }

}