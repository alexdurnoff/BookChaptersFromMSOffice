package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Section;
import org.jsoup.nodes.Document;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;

import java.util.List;

public class DocStartChapterExtractor implements StartChapterExtractor {
    private final List<ParagraphWithSection> paragraphsWithSectionsList;
    private final DocStyleMap docStyleMap;
    private final Index index;

    public DocStartChapterExtractor(List<ParagraphWithSection> paragraphsWithSectionsList,
                                    DocStyleMap docStyleMap,
                                    Index index) {
        this.paragraphsWithSectionsList = paragraphsWithSectionsList;
        this.docStyleMap = docStyleMap;
        this.index = index;
    }


    @Override
    public StartChapter startChapter() {
        Document document = new Document("/tmp/" + "Начало документа" + ".html");
        ParagraphWithSection paragraphWithSection = this.paragraphsWithSectionsList
                .get(index.currentIndex());
        Paragraph paragraph = paragraphWithSection.paragraph();
        while ( ! this.docStyleMap.paragraphIsHeader(paragraph)){
            document.appendChild(
                    new DocElementFactory(paragraphWithSection, index)
                    .docContentElement()
                    .element()
            );
            index.incrementIndex();
            if (index.currentIndex() == this.paragraphsWithSectionsList.size()) break;
            paragraphWithSection = this.paragraphsWithSectionsList.get(index.currentIndex());
            paragraph = paragraphWithSection.paragraph();
        }
        return new StartChapter(document.html());
    }
}
