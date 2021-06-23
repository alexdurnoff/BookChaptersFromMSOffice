package ru.durnov.queue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.jsoup.nodes.Document;
import ru.durnov.chapters.StartChapter;
import ru.durnov.chapters.StartChapterExtractor;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class QueueStartChapterExtractor implements StartChapterExtractor {
    private final HWPFDocument hwpfDocument;
    private final DocStyleMap docStyleMap;
    private final Ranges ranges;
    private final Pictures pictures;

    public QueueStartChapterExtractor(HWPFDocument hwpfDocument,
                                      DocStyleMap docStyleMap,
                                      Ranges ranges,
                                      Pictures pictures) {
        this.hwpfDocument = hwpfDocument;
        this.ranges = ranges;
        this.pictures = pictures;
        this.docStyleMap = docStyleMap;
    }


    @Override
    public StartChapter startChapter() throws ParserConfigurationException, TransformerException {
        Document document = new Document("/tmp/" + "Начало документа" + ".html");
        Range range = this.ranges.nextRange();
        while (! this.docStyleMap.paragraphIsHeader(range)){
            document.appendChild(
                    new QueueElementFactory(
                            range,
                            pictures,
                            hwpfDocument,
                            ranges
                    ).docContentElement().element()
            );
            ranges.removeRange();
            range = ranges.nextRange();
        }
        return new StartChapter(document.html());
    }
}
