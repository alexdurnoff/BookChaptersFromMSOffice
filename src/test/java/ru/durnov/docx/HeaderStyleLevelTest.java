package ru.durnov.docx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeaderStyleLevelTest {

    @Test
    void testAssertReturn2IfHeading1(){
        String styleName = "Heading 1";
        int level = new HeaderStyleLevel(styleName).level();
        assertEquals(1, level);
    }

    @Test
    void testThatThrowIllegalArgumentExceptionIFStyleNameIsNonHeaderStyleName(){
        String styleName = "Heding 1";
        assertThrows(IllegalArgumentException.class, () -> new HeaderStyleLevel(styleName));

    }

    @Test
    void testThatReturn1IfDontHaveNumberInStyleName(){
        String styleName = "Заголовок";
        int level = new HeaderStyleLevel(styleName).level();
        assertEquals(1, level);
    }

}