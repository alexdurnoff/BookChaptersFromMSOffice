package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.util.List;

public class DocxContentChapter implements Chapter {
    private final Level level;
    private final List<IBodyElement> bodyElements;
    private final Index index;
    private final DocxStyleMap styleMap;
    private final DocxContentChapterChecker checker = new DocxContentChapterChecker();


    public DocxContentChapter(Level level, Index index, List<IBodyElement> bodyElements, DocxStyleMap styleMap) {
        this.level = level;
        this.level.incrementLevel();
        this.bodyElements = bodyElements;
        this.index = index;
        this.styleMap = styleMap;
    }

    @Override
    public String title() {
        return null;
    }

    @Override
    public int level() {
        return this.level.currentLevel();
    }

    @Override
    public boolean inline() {
        return true;
    }

    @Override
    public String content() {
        Document document = new Document("/tmp/" + this.title() + ".html");
        Element body = document.appendElement("body");
        IBodyElement bodyElement = bodyElements.get(this.index.currentIndex());
        while (this.styleMap.paragraphIsHeader(bodyElement) || checker.isChapter(bodyElement)){
            body.appendChild(
                    new DocxElementFactory(bodyElement)
                            .docxContentElement()
                            .element()
            );
            index.incrementIndex();
            bodyElement = bodyElements.get(this.index.currentIndex());
        }
        return document.outerHtml();
    }


}
