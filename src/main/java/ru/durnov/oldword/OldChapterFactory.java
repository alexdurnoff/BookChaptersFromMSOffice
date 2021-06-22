package ru.durnov.oldword;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterFactory;
import ru.durnov.chapters.Index;
import ru.durnov.doc.DocContentChapterChecker;
import ru.durnov.doc.DocLevel;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public class OldChapterFactory implements ChapterFactory {
    private final Index index;
    private final DocLevel docLevel;
    private final HWPFDocument hwpfDocument;
    private final List<Paragraph> paragraphList;
    private final DocStyleMap docStyleMap;
    private final DocContentChapterChecker checker;

    public OldChapterFactory(Index index,
                             DocLevel docLevel,
                             HWPFDocument hwpfDocument,
                             List<Paragraph> paragraphList,
                             DocStyleMap docStyleMap) {
        this.index = index;
        this.docLevel = docLevel;
        this.hwpfDocument = hwpfDocument;
        this.paragraphList = paragraphList;
        this.docStyleMap = docStyleMap;
        this.checker = new DocContentChapterChecker();
    }

    @Override
    public Chapter chapter() throws ParserConfigurationException, TransformerException {
        Paragraph paragraph = paragraphList.get(index.currentIndex());
        if (docStyleMap.paragraphIsHeader(paragraph)){
            return new OldHeaderChapter(
                    docLevel.levelByParagraph(paragraph),
                    index,
                    hwpfDocument,
                    paragraphList,
                    docStyleMap,
                    checker
            );
        }

        if (this.checker.isChapter(paragraph)){
            return new OldContentChapter(
                    hwpfDocument,
                    index,
                    this.docLevel.levelByParagraph(paragraph),
                    this.paragraphList,
                    this.docStyleMap,
                    this.checker
            );
        }
        throw new IllegalArgumentException("can't return Chapter because paragraph is not header and not start with number");
    }
}
