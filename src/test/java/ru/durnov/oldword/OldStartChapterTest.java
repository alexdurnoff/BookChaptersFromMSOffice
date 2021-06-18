package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.junit.jupiter.api.Test;
import ru.durnov.chapters.Index;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OldStartChapterTest {

    @Test
    void testStartChapterBuild() throws IOException, ParserConfigurationException, TransformerException {
        HWPFDocument hwpfDocument = new HWPFDocument(new FileInputStream("Test/prikaz1.doc"));
        OldStartChapter oldStartChapter = new OldStartChapter(hwpfDocument, new Index());
        System.out.println(oldStartChapter.startChapter().content());
    }

}