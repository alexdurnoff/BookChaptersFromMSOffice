package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.hwpf.HWPFDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocTest {

    @Test
    void test1() throws Exception {
        Doc doc = new Doc("Test/prikaz1.doc");
        System.out.println(doc.archive().pathToArchive());
    }

    @Test
    void testPrikaz1WithLinks() throws Exception {
        Doc doc = new Doc("Test/prikaz1 with links.doc");
        System.out.println(doc.archive().pathToArchive());
    }
}
