package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;

import java.util.List;

public class DocxStartChapterExtractor implements StartChapterExtractor {
    private final List<IBodyElement> bodyElements;
    private final DocxStyleMap docxStyleMap;
    private final Index index;
    private final CTPageMar ctPageMar;


    public DocxStartChapterExtractor(List<IBodyElement> bodyElements,
                                     DocxStyleMap docxStyleMap,
                                     Index index,
                                     CTPageMar ctPageMar) {
        this.bodyElements = bodyElements;
        this.docxStyleMap = docxStyleMap;
        this.index = index;
        this.ctPageMar = ctPageMar;
    }

    @Override
    public StartChapter startChapter() {
        Document document = new Document("/tmp/" + "Начало документа" + ".html");
        IBodyElement iBodyElement;
        while (! this.docxStyleMap.paragraphIsHeader(iBodyElement = bodyElements.get(index.currentIndex()))){
            document.appendChild(
                    new DocxElementFactory(iBodyElement, ctPageMar)
                            .docxContentElement()
                            .element()
            );
            index.incrementIndex();
            if (index.currentIndex() == bodyElements.size()) break;
        }
        return new StartChapter(document.html());
    }
}
