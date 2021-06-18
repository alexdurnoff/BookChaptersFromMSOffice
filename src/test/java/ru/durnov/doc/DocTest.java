package ru.durnov.doc;

import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DocTest {

    @Test
    void test1() throws IOException, TranscoderException {
        Doc doc = new Doc("Test/prikaz1.doc");
        System.out.println(doc.archive().pathToArchive());
    }
}
