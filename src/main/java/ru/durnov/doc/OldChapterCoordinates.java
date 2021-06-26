package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Index;

import java.util.List;

public class OldChapterCoordinates implements Coordinates {
    private final int start;
    private final int stop;


    public OldChapterCoordinates(HWPFDocument hwpfDocument,
                                 Index index,
                                 DocStyleMap docStyleMap,
                                 DocContentChapterChecker checker) {
        List<Paragraph> paragraphList = new ParagraphList(hwpfDocument).list();
        int numParagraphs = paragraphList.size();
        Paragraph paragraph = paragraphList.get(index.currentIndex());
        int from = new ParagraphCoordinates(hwpfDocument, paragraphList, paragraph).start();
        this.start = from;
        do {
            from += paragraph.text().length();
            index.incrementIndex();
            if (index.currentIndex() == numParagraphs) break;
            paragraph = paragraphList.get(index.currentIndex());
        } while ((! docStyleMap.paragraphIsHeader(paragraph)) &&
                (!checker.isChapter(paragraph)));
        this.stop = from;
    }

    @Override
    public int start() {
        return this.start;
    }

    @Override
    public int stop() {
        return this.stop;
    }
}
