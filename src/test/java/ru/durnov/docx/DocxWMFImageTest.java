package ru.durnov.docx;

import org.junit.jupiter.api.Test;

import java.io.FilePermission;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DocxWMFImageTest {

    @Test
    void testThatRasterizerStarted() throws IOException {
        FilePermission filePermission = new FilePermission("Test/", "write");
        String[] args = new String[1];
        args[0] = "Test/image3.wmf";
        Runtime.getRuntime().exec("java -jar batik-rasterizer.jar Test/image3.wmf");
    }

}