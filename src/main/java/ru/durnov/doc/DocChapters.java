package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Chapters;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class DocChapters implements Chapters {
    private final HWPFDocument hwpfDocument;
    private final ChapterExtractor chapterExtractor;


    public DocChapters(HWPFDocument hwpfDocument) throws ParserConfigurationException, TransformerException {
        this.hwpfDocument = hwpfDocument;
        this.chapterExtractor = new OldContentChapterExtractor(hwpfDocument);
    }


    @Override
    public ChapterExtractor chapterExtractor() {
        return this.chapterExtractor;
    }

}
