package ru.durnov.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.durnov.chapters.Document;
import ru.durnov.doc.Doc;
import ru.durnov.docx.Docx;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void testThatReturnedObjectIsInstanceOfDocxIfIncomingUrlEndWithDotDOCX() {
        String url = "1.docx";
        Document document = new Request(url).document();
        Assertions.assertTrue(document instanceof Docx);
    }

    @Test
    void testThatReturnedObjectIsInstanceOfDocIfIncomingUrlEndWithDotDOC(){
        String url = "1.doc";
        Document document = new Request(url).document();
        Assertions.assertTrue(document instanceof Doc);
    }

    @Test
    public void testThatThrowsExceptionIfDotMissingInDOCSuffix(){
        String url = "14doc";
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Document document = new Request(url).document();
                }
        );
    }

    @Test
    public void testThatThrowsExceptionIfDotMissingInDOCXSuffix(){
        String url = "123docx";
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Document document = new Request(url).document();
                }
        );
    }

    @Test
    void testThatThrowsExceptionIfUrlEndsWithIncorrectExtension(){
        String url = "1.xlsx";
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Document document = new Request(url).document();
                }
        );
    }
}