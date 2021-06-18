package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DocContentChapterCheckerTest {

    @Test
    void test1() throws IOException {
        HWPFDocument hwpfDocument = new HWPFDocument(Files.newInputStream(Path.of("Test/prikaz1.doc")));
        DocContentChapterChecker checker = new DocContentChapterChecker();
        new ParagraphList(hwpfDocument).list().forEach(paragraph -> {
            if (checker.isChapter(paragraph)) {
                System.out.println("is new ChapterHeader = " + checker.isChapter(paragraph));
                System.out.println(paragraph.text());
            }
        });
    }

}