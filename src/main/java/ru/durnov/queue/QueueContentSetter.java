package ru.durnov.queue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.jsoup.nodes.Document;
import ru.durnov.doc.DocContentChapterChecker;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class QueueContentSetter {

    private final Ranges ranges;
    private final Pictures pictures;
    private final HWPFDocument hwpfDocument;
    private final DocContentChapterChecker checker = new DocContentChapterChecker();
    private final Document document;
    private final DocStyleMap docStyleMap;

    public QueueContentSetter(DocStyleMap docStyleMap,
                              Ranges ranges,
                              Pictures pictures,
                              HWPFDocument hwpfDocument) {
        this.docStyleMap = docStyleMap;
        this.ranges = ranges;
        this.hwpfDocument = hwpfDocument;
        this.pictures = pictures;
        this.document = new Document("/tmp/" + ".html");
    }

    public String content() throws ParserConfigurationException, TransformerException {
        Range range = this.ranges.nextRange();
        do {
            document.appendChild(
                    new QueueElementFactory(
                            range,
                            pictures,
                            hwpfDocument,
                            ranges
                    ).docContentElement().element()
            );
            ranges.removeRange();
            if (! ranges.hasNext()) break;
            range = ranges.nextRange();
        } while (! docStyleMap.paragraphIsHeader(range) && !checker.isChapter(range));
        return document.outerHtml();
    }
}
