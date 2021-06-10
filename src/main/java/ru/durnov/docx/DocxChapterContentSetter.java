package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.util.List;

public class DocxChapterContentSetter {
    private final DocxStyleMap docxStyleMap;
    private final String title;
    private final List<IBodyElement> bodyElements;
    private final Index index;

    public DocxChapterContentSetter(DocxStyleMap docxStyleMap,
                                    String title,
                                    List<IBodyElement> bodyElements,
                                    Index index) {
        this.docxStyleMap = docxStyleMap;
        this.title = title;
        this.bodyElements = bodyElements;
        this.index = index;
    }

    public String content() {
        DocxContentChapterChecker checker = new DocxContentChapterChecker();
        Document document = new Document("/tmp/" + this.title + ".html");
        IBodyElement bodyElement = bodyElements.get(this.index.currentIndex());
        do {
            document.appendChild(
                    new DocxElementFactory(bodyElement)
                            .docxContentElement()
                            .element()
            );
            index.incrementIndex();
            if (index.currentIndex() == bodyElements.size()) break;
            bodyElement = bodyElements.get(this.index.currentIndex());
        } while (!this.docxStyleMap.paragraphIsHeader(bodyElement) && !checker.isChapter(bodyElement));
        return document.outerHtml();
    }
}
