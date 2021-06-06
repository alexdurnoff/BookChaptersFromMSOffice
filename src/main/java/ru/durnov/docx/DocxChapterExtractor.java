package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DocxChapterExtractor implements ChapterExtractor {
    private final String url;
    private final Level level = new Level();

    public DocxChapterExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Chapter> chapterList() throws IOException {
        List<Chapter> chapterList = new ArrayList<>();
        List<IBodyElement> bodyElements = new XWPFDocument(Files.newInputStream(Path.of(url))).getBodyElements();
        for (Index index = new Index(); index.currentIndex() < bodyElements.size(); index.incrementIndex()){
            Chapter chapter = new DocxChapterFactory(index, level, bodyElements).chapter();
            this.chapterList().add(chapter);
        }
        return chapterList;
    }

}
