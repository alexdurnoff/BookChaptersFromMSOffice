package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterFactory;
import ru.durnov.chapters.Index;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
@Deprecated
public class DocChapterFactory implements ChapterFactory {
    private final Index index;
    private final DocStyleMap docStyleMap;
    private final List<ParagraphWithSection> paragraphWithSectionList;
    private final DocLevel docLevel;
    private final PicturesTable picturesTable;
    private final HWPFDocument hwpfDocument;

    public DocChapterFactory(DocLevel docLevel,
                             Index index,
                             DocStyleMap docStyleMap,
                             List<ParagraphWithSection> paragraphWithSectionList,
                             PicturesTable picturesTable,
                             HWPFDocument hwpfDocument) {
        this.docLevel = docLevel;
        this.index = index;
        this.docStyleMap = docStyleMap;
        this.paragraphWithSectionList = paragraphWithSectionList;
        this.picturesTable = picturesTable;
        this.hwpfDocument = hwpfDocument;
    }


    @Override
    public Chapter chapter() throws ParserConfigurationException, TransformerException {
        ParagraphWithSection paragraphWithSection = this.paragraphWithSectionList
                .get(index.currentIndex());
        Paragraph paragraph = paragraphWithSection.paragraph();
        if (docStyleMap.paragraphIsHeader(paragraph)){
            return new DocHeaderChapter(
                    docLevel.levelByParagraph(paragraph),
                    index,
                    paragraphWithSectionList,
                    docStyleMap,
                    picturesTable,
                    hwpfDocument
            );
        }

        if (new DocContentChapterChecker().isChapter(paragraph)){
            return new DocContentChapter(
                    docLevel.levelByParagraph(paragraph),
                    index,
                    paragraphWithSectionList,
                    docStyleMap,
                    picturesTable,
                    this.hwpfDocument
            );
        }
        throw new IllegalArgumentException("can't return Chapter because is not header and not start with number");

    }
}
