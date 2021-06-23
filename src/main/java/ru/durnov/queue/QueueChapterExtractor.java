package ru.durnov.queue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.StartChapterExtractor;
import ru.durnov.doc.DocLevel;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;

public class QueueChapterExtractor implements ChapterExtractor {
    private final HWPFDocument hwpfDocument;
    private final Ranges ranges;
    private final Pictures pictures;
    private final List<Chapter> chapterList = new ArrayList<>();
    private final StartChapterExtractor startChapterExtractor;
    private final DocLevel docLevel;
    private final DocStyleMap docStyleMap;


    public QueueChapterExtractor(HWPFDocument hwpfDocument){
        this.hwpfDocument = hwpfDocument;
        this.docStyleMap = new DocStyleMap(hwpfDocument);
        this.docLevel = new DocLevel(docStyleMap);
        this.ranges = new Ranges(this.hwpfDocument);
        this.pictures = new Pictures(hwpfDocument);
        this.startChapterExtractor = new QueueStartChapterExtractor(
                this.hwpfDocument,
                this.docStyleMap,
                ranges,
                pictures
        );
    }

    @Override
    public List<Chapter> chapterList() throws IOException, ParserConfigurationException, TransformerException {
        this.chapterList.add(this.startChapterExtractor.startChapter());
        while (this.ranges.hasNext()){
            Chapter chapter = new QueueChapterFactory(
                    this.docLevel,
                    this.docStyleMap,
                    this.hwpfDocument,
                    this.ranges,
                    this.pictures
            ).chapter();
            this.chapterList.add(chapter);
        }
        return this.chapterList;
    }
}
