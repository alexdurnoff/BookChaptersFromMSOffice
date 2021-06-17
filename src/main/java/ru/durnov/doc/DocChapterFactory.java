package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterFactory;
import ru.durnov.chapters.Index;


import java.util.List;

public class DocChapterFactory implements ChapterFactory {
    private final Index index;
    private final DocStyleMap docStyleMap;
    private final List<ParagraphWithSection> paragraphWithSectionList;
    private final DocLevel docLevel;

    public DocChapterFactory(Index index,
                             DocStyleMap docStyleMap,
                             List<ParagraphWithSection> paragraphWithSectionList) {
        this.index = index;
        this.docStyleMap = docStyleMap;
        this.docLevel = new DocLevel(this.docStyleMap);
        this.paragraphWithSectionList = paragraphWithSectionList;
    }


    @Override
    public Chapter chapter() {
        ParagraphWithSection paragraphWithSection = this.paragraphWithSectionList
                .get(index.currentIndex());
        Paragraph paragraph = paragraphWithSection.paragraph();
        if (docStyleMap.paragraphIsHeader(paragraph)){
            return new DocHeaderChapter(
                    docLevel.levelByParagraph(paragraph),
                    index,
                    paragraphWithSectionList,
                    docStyleMap
            );
        }

        if (new DocContentChapterChecker().isChapter(paragraph)){
            return new DocContentChapter(
                    docLevel.levelByParagraph(paragraph),
                    index,
                    paragraphWithSectionList,
                    docStyleMap
            );
        }
        throw new IllegalArgumentException("can't return Chapter because is not header and not start with number");

    }
}
