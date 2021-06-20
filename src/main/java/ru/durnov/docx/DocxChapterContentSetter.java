package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.jsoup.nodes.Document;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.chapters.Index;

import java.util.List;

public class DocxChapterContentSetter {
    private final DocxStyleMap docxStyleMap;
    private final String title;
    private final List<IBodyElement> bodyElements;
    private final Index index;
    private final CTSectPr ctSectPr;

    public DocxChapterContentSetter(DocxStyleMap docxStyleMap,
                                    String title,
                                    List<IBodyElement> bodyElements,
                                    Index index,
                                    CTSectPr ctSectPr) {
        this.docxStyleMap = docxStyleMap;
        this.title = title;
        this.bodyElements = bodyElements;
        this.index = index;
        this.ctSectPr = ctSectPr;
    }

    public String content() {
        DocxContentChapterChecker checker = new DocxContentChapterChecker();
        Document document = new Document("/tmp/" + this.title + ".html");
        IBodyElement bodyElement = bodyElements.get(this.index.currentIndex());
        do {
            document.appendChild(
                    new DocxElementFactory(bodyElement, ctSectPr)
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
