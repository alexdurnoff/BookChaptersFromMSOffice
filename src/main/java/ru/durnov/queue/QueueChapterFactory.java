package ru.durnov.queue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import ru.durnov.chapters.Chapter;
import ru.durnov.doc.DocContentChapterChecker;
import ru.durnov.doc.DocLevel;
import ru.durnov.doc.DocStyleMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class QueueChapterFactory {
    private final DocLevel docLevel;
    private final DocStyleMap docStyleMap;
    private final HWPFDocument hwpfDocument;
    private final Ranges ranges;
    private final Pictures pictures;
    private final DocContentChapterChecker checker = new DocContentChapterChecker();

    public QueueChapterFactory(DocLevel docLevel,
                               DocStyleMap docStyleMap,
                               HWPFDocument hwpfDocument,
                               Ranges ranges,
                               Pictures pictures) {
        this.docLevel = docLevel;
        this.docStyleMap = docStyleMap;
        this.hwpfDocument = hwpfDocument;
        this.ranges = ranges;
        this.pictures = pictures;
    }


    public Chapter chapter() throws ParserConfigurationException, TransformerException {
        Range range = this.ranges.nextRange();
        int level = this.docLevel.levelByRange(range);
        if (this.docStyleMap.paragraphIsHeader(range)){
            return new QueueHeaderChapter(
                    level,
                    docStyleMap,
                    hwpfDocument,
                    range,
                    ranges,
                    pictures
            );
        }
        if (checker.isChapter(range)){
            return new QueueContentChapter(
                    level,
                    docStyleMap,
                    hwpfDocument,
                    range,
                    ranges,
                    pictures
            );
        }
        throw new IllegalArgumentException("can't return Chapter because is not header and not start with number");
    }
}
