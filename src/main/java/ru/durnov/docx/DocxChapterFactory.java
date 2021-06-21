package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterFactory;
import ru.durnov.chapters.Index;

import java.util.List;

public class DocxChapterFactory implements ChapterFactory {
    private final DocxLevel docxLevel;
    private final Index index;
    private final List<IBodyElement> bodyElements;
    private final DocxStyleMap docxStyleMap;
    private final CTSectPr ctSectPr;


    public DocxChapterFactory(DocxLevel docxLevel,
                              Index index,
                              List<IBodyElement> bodyElements,
                              DocxStyleMap docxStyleMap,
                              CTSectPr ctSectPr) {
        this.docxLevel = docxLevel;
        this.index = index;
        this.bodyElements = bodyElements;
        this.docxStyleMap = docxStyleMap;
        this.ctSectPr = ctSectPr;
    }

    @Override
    public Chapter chapter() {
        if (bodyElements.get(index.currentIndex()) instanceof XWPFParagraph) {
            XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElements.get(index.currentIndex());
            if (docxStyleMap.paragraphIsHeader(xwpfParagraph)){
                return new DocxHeaderChapter(
                        docxLevel.levelByParagraph(xwpfParagraph),
                        index,
                        bodyElements,
                        docxStyleMap,
                        ctSectPr
                );
            }
            if (new DocxContentChapterChecker().isChapter(xwpfParagraph)) {
                return new DocxContentChapter(
                        docxLevel.levelByParagraph(xwpfParagraph),
                        index,
                        bodyElements,
                        docxStyleMap,
                        ctSectPr
                );
            }
            throw new IllegalArgumentException("can't return Chapter because paragraph is not header and not start with number");
        } else {
            throw new IllegalArgumentException("can't return Chapter because bodyElement is not XWPFParagraph");
        }
    }
}
