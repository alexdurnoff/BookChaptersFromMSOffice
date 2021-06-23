package ru.durnov.queue;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import ru.durnov.doc.DocContentElement;
import ru.durnov.doc.DocListContentELement;
import ru.durnov.doc.DocParagraphContentElement;
import ru.durnov.doc.DocTableContentElement;

import java.util.Map;
import java.util.Queue;

public class QueueElementFactory {
    private final Range range;
    private final Pictures pictures;
    private final HWPFDocument hwpfDocument;
    private final Ranges ranges;


    public QueueElementFactory(Range range,
                               Pictures pictures,
                               HWPFDocument hwpfDocument,
                               Ranges ranges) {
        this.range = range;
        this.pictures = pictures;
        this.hwpfDocument = hwpfDocument;
        this.ranges = ranges;
    }

    public DocContentElement docContentElement(){
        if (range instanceof Table){
            Table table = (Table) range;
            ranges.removeTableParagraphs();
            return new DocTableContentElement(hwpfDocument, table);
        }
        Paragraph paragraph = (Paragraph) range;
        return new DocParagraphContentElement(
                paragraph,
                this.ranges.sectionByRange(paragraph),
                this.pictures
        );
    }
}
