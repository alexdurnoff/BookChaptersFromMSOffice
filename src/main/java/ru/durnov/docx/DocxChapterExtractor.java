package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Index;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DocxChapterExtractor implements ChapterExtractor {
    private final XWPFDocument xwpfDocument;
    private final CTSectPr ctSectPr;





    public DocxChapterExtractor(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
        this.ctSectPr = xwpfDocument.getDocument().getBody().getSectPr();
    }

    @Override
    public List<Chapter> chapterList() throws IOException {
        List<Chapter> chapterList = new ArrayList<>();
        List<IBodyElement> bodyElements = xwpfDocument.getBodyElements();
        DocxStyleMap docxStyleMap = new DocxStyleMap(xwpfDocument);
        DocxLevel docxLevel = new DocxLevel(docxStyleMap);
        Index index = new Index();
        chapterList.add(new DocxStartChapterExtractor(bodyElements, docxStyleMap, index, ctSectPr).startChapter());
        while (index.currentIndex() < bodyElements.size()){
            Chapter chapter = new DocxChapterFactory(
                   docxLevel,
                    index,
                    bodyElements,
                    docxStyleMap,
                    ctSectPr
            ).chapter();
            chapterList.add(chapter);
        }
        return chapterList;
    }

}
