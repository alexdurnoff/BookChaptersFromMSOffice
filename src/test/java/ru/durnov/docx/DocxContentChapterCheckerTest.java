package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker(xwpfParagraph);
        assertTrue(docxContentChapterChecker.isChapter());
    }

    @Test
    void testWithSpaceInStartString(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(" 1. Общие положения");
        xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("\n" + "Просто какой-то текст");
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker(xwpfParagraph);
        assertTrue(docxContentChapterChecker.isChapter());
    }

    @Test
    void testWithNumbersSeparatedByDot(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(" 1.1.2Общие положения");
        xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText("\n" + "Просто какой-то текст");
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker(xwpfParagraph);
        assertTrue(docxContentChapterChecker.isChapter());
    }

    @Test
    void testStringWithoutBreakeStringSymbol(){
        XWPFDocument xwpfDocument = new XWPFDocument();
        XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
        XWPFRun xwpfRun = xwpfParagraph.createRun();
        xwpfRun.setText(" 1.1.2Общие положения");
        DocxContentChapterChecker docxContentChapterChecker = new DocxContentChapterChecker(xwpfParagraph);
        assertTrue(docxContentChapterChecker.isChapter());
    }


}