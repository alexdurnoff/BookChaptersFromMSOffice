package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.ChapterExtractor;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.Level;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DocxChapterExtractor implements ChapterExtractor {
    private final String url;


    public DocxChapterExtractor(String url) {
        this.url = url;
    }

    @Override
    public List<Chapter> chapterList() throws IOException {
        List<Chapter> chapterList = new ArrayList<>();
        XWPFDocument xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of(url)));
        List<IBodyElement> bodyElements = xwpfDocument.getBodyElements();
        DocxStyleMap docxStyleMap = new DocxStyleMap(xwpfDocument);
        Index index = new Index();
        chapterList.add(new DocxStartChapterExtractor(bodyElements, docxStyleMap, index).startChapter());
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElements.get(index.currentIndex());
        System.out.println("paragraph after adding startChapter is " + xwpfParagraph.getText());
        while (index.currentIndex() < bodyElements.size()){
            System.out.println("index is " + index.currentIndex());
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
