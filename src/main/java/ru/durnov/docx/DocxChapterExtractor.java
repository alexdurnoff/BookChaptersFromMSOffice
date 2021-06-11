package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
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


    public DocxChapterExtractor(XWPFDocument xwpfDocument) {
        this.xwpfDocument = xwpfDocument;
    }

    @Override
    public List<Chapter> chapterList() throws IOException {
        List<Chapter> chapterList = new ArrayList<>();
        List<IBodyElement> bodyElements = xwpfDocument.getBodyElements();
        DocxStyleMap docxStyleMap = new DocxStyleMap(xwpfDocument);
        Index index = new Index();
        chapterList.add(new DocxStartChapterExtractor(bodyElements, docxStyleMap, index).startChapter());
        while (index.currentIndex() < bodyElements.size()){
            Chapter chapter = new DocxChapterFactory(
                    index,
                    docxStyleMap,
                    bodyElements
            ).chapter();
            chapterList.add(chapter);
            //index.incrementIndex();
            // Все-таки мы не инкрементируем здесь. В DocxContentChapterSetter все заработало через do-while.
        }
        return chapterList;
    }

}
