package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;

import java.util.List;

public class DocxStartChapterExtractor implements StartChapterExtractor {
    private final List<IBodyElement> bodyElements;
    private final DocxStyleMap docxStyleMap;
    private final Index index;
    private final CTSectPr ctSectPr;


    public DocxStartChapterExtractor(List<IBodyElement> bodyElements,
                                     DocxStyleMap docxStyleMap,
                                     Index index,
                                     CTSectPr ctSectPr) {
        this.bodyElements = bodyElements;
        this.docxStyleMap = docxStyleMap;
        this.index = index;
        this.ctSectPr = ctSectPr;
    }

    @Override
    public StartChapter startChapter() {
        Document document = new Template().document();
        IBodyElement iBodyElement;
        while (! this.docxStyleMap.paragraphIsHeader(iBodyElement = bodyElements.get(index.currentIndex()))){
            document.appendChild(
                    new DocxElementFactory(iBodyElement, ctSectPr)
                            .docxContentElement()
                            .element()
            );
            index.incrementIndex();
            if (index.currentIndex() == bodyElements.size()) break;
        }
        return new StartChapter(document.html());
    }
}
