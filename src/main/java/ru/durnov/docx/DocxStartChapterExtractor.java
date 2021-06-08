package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;

import java.util.List;

public class DocxStartChapterExtractor implements StartChapterExtractor {
    private final List<IBodyElement> bodyElements;
    private final DocxStyleMap docxStyleMap;
    private final Index index;


    public DocxStartChapterExtractor(List<IBodyElement> bodyElements, DocxStyleMap docxStyleMap, Index index) {
        this.bodyElements = bodyElements;
        this.docxStyleMap = docxStyleMap;
        this.index = index;
    }

    @Override
    public StartChapter startChapter() {
        Document document = new Document("/tmp/" + "Начало документа" + ".html");
        Element body = document.appendElement("body");
        IBodyElement iBodyElement = bodyElements.get(index.currentIndex());
        while (! this.docxStyleMap.paragraphIsHeader(iBodyElement)){
            body.appendChild(
                    new DocxElementFactory(iBodyElement)
                            .docxContentElement()
                            .element()
            );
            index.incrementIndex();
            iBodyElement = bodyElements.get(index.currentIndex());
        }
        return new StartChapter(document.outerHtml());
    }
}
