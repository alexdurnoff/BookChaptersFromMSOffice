package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocxContentChapterCheckerTest {

    @Test
    void test1(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("1. Общие положения");
        xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("\n" + "Просто какой-то текст");
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker();
        assertTrue(docxContentChapterChecker.isChapter(xwpfParagraph));
    }

    @Test
    void testWithSpaceInStartString(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(" 1. Общие положения");
        xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("\n" + "Просто какой-то текст");
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker();
        assertTrue(docxContentChapterChecker.isChapter(xwpfParagraph));
    }

    @Test
    void testWithNumbersSeparatedByDot(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(" 1.1.2Общие положения");
        xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("\n" + "Просто какой-то текст");
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker();
        assertTrue(docxContentChapterChecker.isChapter(xwpfParagraph));
    }

    @Test
    void testStringWithoutBreakeStringSymbol(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(" 1.1.2Общие положения");
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker();
        assertTrue(docxContentChapterChecker.isChapter(xwpfParagraph));
    }

    @Test
    void testThatIfBodyElementIsTableThanReturnFalse(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFTable table = xwpfDocument.createTable();
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker();
        assertFalse(docxContentChapterChecker.isChapter(table));
    }


}