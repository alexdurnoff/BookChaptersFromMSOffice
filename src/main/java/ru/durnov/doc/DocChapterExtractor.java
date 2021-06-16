package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Index;
import ru.durnov.docx.DocxStartChapterExtractor;

import java.util.ArrayList;
import java.util.List;

public class DocChapterExtractor implements ChapterExtractor {
    private final HWPFDocument hwpfDocument;
    private final Index index = new Index();
    private final DocStartChapterExtractor extractor;

    public DocChapterExtractor(HWPFDocument hwpfDocument) {
        this.hwpfDocument = hwpfDocument;
        this.extractor = new DocStartChapterExtractor(
                new SectionList(hwpfDocument).list(),
                new DocStyleMap(hwpfDocument),
                this.index
        );
    }


    @Override
    public List<Chapter> chapterList() {

        return null;
    }
}
