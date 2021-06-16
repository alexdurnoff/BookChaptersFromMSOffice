package ru.durnov.doc;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Section;
import org.jsoup.nodes.Document;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;

import java.util.List;

public class DocStartChapterExtractor implements StartChapterExtractor {
    private final List<Paragraph> paragraphList;
    private final DocStyleMap docStyleMap;
    private final Index index;

    public DocStartChapterExtractor(List<Section> sectionList, DocStyleMap docStyleMap, Index index) {
        this.paragraphList = new ParagraphList(sectionList).list();
        this.docStyleMap = docStyleMap;
        this.index = index;
    }


    @Override
    public StartChapter startChapter() {
        Document document = new Document("/tmp/" + "Начало документа" + ".html");
        Paragraph paragraph = this.paragraphList.get(this.index.currentIndex());
        while ( ! this.docStyleMap.paragraphIsHeader(paragraph)){
            document.appendChild(
                    new DocElementFactory(paragraph)
                    .docContentElement()
                    .element()
            );
            index.incrementIndex();
            if (index.currentIndex() == this.paragraphList.size()) break;
            paragraph = this.paragraphList.get(this.index.currentIndex());
        }
        return new StartChapter(document.html());
    }
}
