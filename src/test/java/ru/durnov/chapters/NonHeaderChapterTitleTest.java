package ru.durnov.chapters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonHeaderChapterTitleTest {

    @Test
    void testTitleIfParagraphHasOnlyOneNumber(){
        String paragraphText = "1 Общие положенгия";
        String title = new NonHeaderChapterTitle(paragraphText).title();
        assertTrue(title.equals("1"));
    }

    @Test
    void testTitleIfParagraphHasOnlyOneNumberWithDot(){
        String paragraphText = "1. Общие положенгия";
        String title = new NonHeaderChapterTitle(paragraphText).title();
        assertTrue(title.equals("1."));
    }

    @Test
    void testTitleIfParagraphHasWithSpaceBeforeNumber(){
        String paragraphText = " 1.1 Общие положенгия";
        String title = new NonHeaderChapterTitle(paragraphText).title();
        assertTrue(title.equals("1.1"));
    }

    @Test
    void testTitleIfParagraphHasTwoNumbers(){
        String paragraphText = "1.1 Общие положенгия";
        String title = new NonHeaderChapterTitle(paragraphText).title();
        assertTrue(title.equals("1.1"));
    }

    @Test
    void testThatThrowsIllegalArgumentExceptionIfNumberIsNotInStartOfParagraph(){
        String paragraphText = "Общие 1.1.1 положенгия";
        assertThrows(IllegalArgumentException.class, () -> new NonHeaderChapterTitle(paragraphText).title());
    }

    @Test
    void testTitleWithSpaceBetweenNumbers(){
        String paragraphText = "1. 1.3 Общие положенгия";
        String title = new NonHeaderChapterTitle(paragraphText).title();
        assertTrue(title.equals("1. 1.3"));
    }

}