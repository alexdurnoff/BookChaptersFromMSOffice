package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Index;

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

    public OldContentChapterExtractor(HWPFDocument hwpfDocument) throws ParserConfigurationException, TransformerException {
        this.hwpfDocument = hwpfDocument;
        this.paragraphList = new ParagraphList(hwpfDocument).list();
        this.docStyleMap = new DocStyleMap(hwpfDocument);
        this.docLevel = new DocLevel(this.docStyleMap);
        this.chapterList.add(
                new OldStartChapterExtractor(
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
    }

    @Override
    public List<Chapter> chapterList() throws IOException, ParserConfigurationException, TransformerException {
        return this.chapterList;
    }
}
