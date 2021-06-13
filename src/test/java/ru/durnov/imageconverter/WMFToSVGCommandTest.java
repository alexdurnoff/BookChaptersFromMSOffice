package ru.durnov.imageconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WMFToSVGCommandTest {

    @Test
    void testConvertFromWMFToSVG(){
        WMFToSVGCommand command = new WMFToSVGCommand("Test/image3.wmf");
        String[] args = command.commandLineArray();
        assertEquals(args[0], "Test/image3.wmf");
        assertEquals(args[1], "Test/image3.svg");
    }

}