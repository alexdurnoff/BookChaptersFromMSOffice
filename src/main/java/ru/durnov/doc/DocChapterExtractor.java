package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Index;
import ru.durnov.docx.DocxStartChapterExtractor;

import java.util.ArrayList;
import java.util.List;

public class DocChapterExtractor implements ChapterExtractor {
    private final Index index = new Index();
    private final DocStartChapterExtractor startExtractor;
    private final List<Chapter> chapterList = new ArrayList<>();
    private final DocStyleMap docStyleMap;
    private final DocLevel docLevel;
    private final List<ParagraphWithSection> paragraphWithSectionList;


    public DocChapterExtractor(HWPFDocument hwpfDocument) {
        this.docStyleMap = new DocStyleMap(hwpfDocument);
        this.docLevel  = new DocLevel(docStyleMap);
        this.paragraphWithSectionList = new ParagraphsWithSections(hwpfDocument).list();
        this.startExtractor = new DocStartChapterExtractor(
                this.paragraphWithSectionList,
                new DocStyleMap(hwpfDocument),
                this.index
        );
    }

    @Override
    public List<Chapter> chapterList() {
        chapterList.add(startExtractor.startChapter());
        while (index.currentIndex() < paragraphWithSectionList.size()){
            Chapter chapter = new DocChapterFactory(
                    this.docLevel,
                    this.index,
                    this.docStyleMap,
                    this.paragraphWithSectionList
            ).chapter();
            this.chapterList.add(chapter);
        }
        return chapterList;
    }
}
