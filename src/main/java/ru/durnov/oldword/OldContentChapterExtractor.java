package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Index;
import ru.durnov.doc.DocLevel;
import ru.durnov.doc.DocStyleMap;
import ru.durnov.doc.ParagraphList;
import ru.durnov.queue.Pictures;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OldContentChapterExtractor implements ChapterExtractor {
    private final HWPFDocument hwpfDocument;
    private final List<Chapter> chapterList = new ArrayList<>();
    private final DocStyleMap docStyleMap;
    private final DocLevel docLevel;
    private final List<Paragraph> paragraphList;
    private final Index index = new Index();

    public OldContentChapterExtractor(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
        this.paragraphList = new ParagraphList(hwpfDocument).list();
        this.docStyleMap = new DocStyleMap(hwpfDocument);
        this.docLevel = new DocLevel(this.docStyleMap);
    }

    @Override
    public List<Chapter> chapterList() throws IOException, ParserConfigurationException, TransformerException {
        this.chapterList.add(
                new OldStartChapter(
                        hwpfDocument,
                        index,
                        docStyleMap,
                        paragraphList
                ).startChapter()
        );
        int numParagraphs = this.paragraphList.size();
        while (index.currentIndex() < numParagraphs){
            Chapter chapter = new OldChapterFactory(
                    index,
                    docLevel,
                    hwpfDocument,
                    paragraphList,
                    docStyleMap
            ).chapter();
            chapterList.add(chapter);
        }
        Pictures pictures = new Pictures(hwpfDocument);
        pictures.addPicturesReferencesToChapters(this.chapterList);
        return this.chapterList;
    }
}
