package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DocxChapterExtractor implements ChapterExtractor {
    private final String url;

    public DocxChapterExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Chapter> chapterList() throws IOException {
        List<Chapter> chapterList = new ArrayList<>();
        new XWPFDocument(
                Files.newInputStream(Path.of(url))
        ).getParagraphs().forEach(xwpfParagraph -> {
            chapterList.add(new XWPFParagraphWithChapters(xwpfParagraph).chapter());
        });
        return chapterList;
    }

}
