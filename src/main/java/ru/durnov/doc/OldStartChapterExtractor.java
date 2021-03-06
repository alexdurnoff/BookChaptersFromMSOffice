package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import ru.durnov.chapters.Index;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public class OldStartChapterExtractor implements StartChapterExtractor {
    private final HWPFDocument hwpfDocument;
    private final Index index;
    private final DocStyleMap docStyleMap;
    private final int numParagraphs;
    private final List<Paragraph> paragraphList;


    public OldStartChapterExtractor(HWPFDocument hwpfDocument,
                                    Index index,
                                    DocStyleMap docStyleMap,
                                    List<Paragraph> paragraphList) {
        this.hwpfDocument = hwpfDocument;
        this.index = index;
        this.docStyleMap = docStyleMap;
        this.paragraphList = paragraphList;
        this.numParagraphs = paragraphList.size();
    }

    public OldStartChapterExtractor(HWPFDocument hwpfDocument,
                                    Index index,
                                    DocStyleMap docStyleMap) {
        this.hwpfDocument = hwpfDocument;
        this.index = index;
        this.docStyleMap = docStyleMap;
        this.paragraphList = new ParagraphList(hwpfDocument).list();
        this.numParagraphs = this.paragraphList.size();
    }

    public OldStartChapterExtractor(HWPFDocument hwpfDocument, Index index){
        this.hwpfDocument = hwpfDocument;
        this.index = index;
        this.paragraphList = new ParagraphList(hwpfDocument).list();
        this.docStyleMap = new DocStyleMap(hwpfDocument);
        this.numParagraphs = this.paragraphList.size();
    }

    @Override
    public StartChapter startChapter() throws ParserConfigurationException, TransformerException {
        Paragraph paragraph = paragraphList.get(index.currentIndex());
        int from = 0;
        Coordinates coordinates = new ParagraphCoordinates(
                hwpfDocument,
                hwpfDocument.getRange().getParagraph(0),
                0
        );
        while (!this.docStyleMap.paragraphIsHeader(paragraph)){
            coordinates = new ParagraphCoordinates(hwpfDocument, paragraph, from);
            from = coordinates.start() + 1;
            index.incrementIndex();
            if (index.currentIndex() == numParagraphs) break;
            paragraph = this.paragraphList.get(index.currentIndex());
        }
        return new OldStartChapter(
                new OldContentSetter(
                        hwpfDocument,
                        0,
                        coordinates.stop()
                ).content()
        );
    }
}
